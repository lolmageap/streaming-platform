package com.cherhy.payment

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.common.util.extension.toSeconds
import com.cherhy.payment.adapter.out.persistence.TestCoroutineRepository
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.util.WithTestContainers
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class CacheWithLock(
    @Autowired private val objectMapper: ObjectMapper,
    @Autowired private val findTestUseCase: FindTestUseCase,
    @Autowired private val stringRedisTemplate: StringRedisTemplate,
    @MockkBean private val testRepository: TestCoroutineRepository,
) : WithTestContainers, BehaviorSpec({
    afterEach {
        stringRedisTemplate.deleteAll()
        clearMocks(testRepository)
    }

    Given("@CacheableWithLock이 적용된 메서드가 존재 하고 ") {
        val testEntity = EntityFactory.generateTestR2dbcEntity(id = 1)

        val serializedValue = objectMapper.writeValueAsString(testEntity)
        val request =
            FindTestCommand(
                id = TestId.of(testEntity.id.toString())
            )

        When("값이 캐시에 없는 경우 ") {
            coEvery { testRepository.findById(testEntity.id) } returns testEntity
            findTestUseCase.executeWithCacheLock(request)

            Then("데이터베이스에서 값을 가져오고 캐시에 저장 된다") {
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
                val value = stringRedisTemplate.opsForValue().get("$TEST::${testEntity.id}")
                value shouldBe serializedValue
            }

            Then("여러 요청이 들어와도 데이터베이스에는 한 번만 요청 된다") {
                repeat(10) { launch { findTestUseCase.executeWithCacheLock(request) } }
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
            }
        }

        When("값이 캐시에 있는 경우 ") {
            stringRedisTemplate.opsForValue().set("$TEST::${testEntity.id}", serializedValue, 1.toSeconds())
            findTestUseCase.executeWithCacheLock(request)

            Then("메서드가 실행 되지 않고 캐시에 저장된 결과가 반환 된다") {
                coVerify(exactly = 0) { testRepository.findById(testEntity.id) }
            }
        }

        When("TTL에 적용된 시간이 지나서 삭제 되면 ") {
            coEvery { testRepository.findById(testEntity.id) } returns testEntity
            stringRedisTemplate.opsForValue().set("$TEST::${testEntity.id}", serializedValue, 1.toSeconds())

            delay(1000)
            findTestUseCase.executeWithCacheLock(request)

            Then("메서드가 실행 되고 결과가 캐시에 저장 되어 반환 된다") {
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
            }
        }
    }

    Given("@CacheEvict이 적용된 메서드가 존재 하고 ") {
        val testEntity = EntityFactory.generateTestR2dbcEntity(id = 1)
        val request = FindTestCommand(id = TestId(testEntity.id.toString()))

        When("값이 캐시에 있는 경우 ") {
            stringRedisTemplate.opsForValue()
                .set("$TEST::${testEntity.id}", objectMapper.writeValueAsString(testEntity), 1.toSeconds())
            findTestUseCase.executeWithCacheLock(request)

            Then("캐시가 삭제 된다") {
                coVerify(exactly = 0) { testRepository.findById(testEntity.id) }
            }
        }
    }

    Given("@CachePut이 적용된 메서드가 존재 하고 ") {
        val testEntity = EntityFactory.generateTestR2dbcEntity(id = 1)
        val request = FindTestCommand(id = TestId(testEntity.id.toString()))

        When("값이 캐시에 있는 경우 ") {
            stringRedisTemplate.opsForValue()
                .set("$TEST::${testEntity.id}", objectMapper.writeValueAsString(testEntity), 1.toSeconds())
            findTestUseCase.executeWithCacheLock(request)

            Then("캐시가 갱신 된다") {
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
            }
        }
    }
})