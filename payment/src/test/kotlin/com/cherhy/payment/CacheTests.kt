package com.cherhy.payment

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.common.util.extension.toSeconds
import com.cherhy.payment.FlywayConfigurer.flyway
import com.cherhy.payment.TestContainers.postgresContainer
import com.cherhy.payment.TestContainers.redisContainer
import com.cherhy.payment.adapter.out.persistence.TestCoroutineRepository
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.domain.TestId
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.testcontainers.perSpec
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.delay
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class CacheTests(
    private val objectMapper: ObjectMapper,
    private val findTestUseCase: FindTestUseCase,
    private val stringRedisTemplate: StringRedisTemplate,
    @MockkBean private val testRepository: TestCoroutineRepository,
) : BehaviorSpec({
    beforeSpec {
        postgresContainer.start()
        listener(postgresContainer.perSpec())

        redisContainer.start()
        listener(redisContainer.perSpec())

        flyway.migrate()
    }

    afterEach {
        stringRedisTemplate.deleteAll()
        clearMocks(testRepository)
    }

    Given("@Cacheable이 적용된 메서드가 존재 하고 ") {
        val testEntity = EntityFactory.generateTestR2dbcEntity(id = 1)

        val serializedValue = objectMapper.writeValueAsString(testEntity)
        val request = FindTestCommand(id = TestId(testEntity.id.toString()))

        When("값이 캐시에 없는 경우 ") {
            coEvery { testRepository.findById(testEntity.id) } returns testEntity
            findTestUseCase.execute(request)

            Then("메서드가 실행 되고 결과가 캐시에 저장 되어 반환 된다") {
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
            }
        }

        When("값이 캐시에 있는 경우 ") {
            stringRedisTemplate.opsForValue().set("$TEST::${testEntity.id}", serializedValue, 1.toSeconds())
            findTestUseCase.execute(request)

            Then("메서드가 실행 되지 않고 캐시에 저장된 결과가 반환 된다") {
                coVerify(exactly = 0) { testRepository.findById(testEntity.id) }
            }
        }

        When("TTL에 적용된 시간이 지나서 삭제 되면 ") {
            coEvery { testRepository.findById(testEntity.id) } returns testEntity
            stringRedisTemplate.opsForValue().set("$TEST::${testEntity.id}", serializedValue, 1.toSeconds())

            delay(1000)
            findTestUseCase.execute(request)

            Then("메서드가 실행 되고 결과가 캐시에 저장 되어 반환 된다") {
                coVerify(exactly = 1) { testRepository.findById(testEntity.id) }
            }
        }
    }
})