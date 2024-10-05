package com.cherhy.payment

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.payment.adapter.out.CacheWriter
import com.cherhy.payment.adapter.out.persistence.TestCoroutineRepository
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.domain.TestId
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer

@SpringBootTest
class CacheTests(
    private val findTestUseCase: FindTestUseCase,
    private val testCoroutineRepository: TestCoroutineRepository,
    private val cacheWriter: CacheWriter,
) : BehaviorSpec({
    val redisContainer = GenericContainer<Nothing>("redis:5.0.3-alpine")
    beforeSpec {
        redisContainer.portBindings.add("16379:6379")
        redisContainer.start()
    }

    Given("@Cacheable이 적용된 메서드가 있을 때") {
        val new = EntityFactory.generateTestR2dbcEntity()
        val testEntity = testCoroutineRepository.save(new)

        val request = FindTestCommand(id = TestId(testEntity.id.toString()))

        When("값이 캐시에 없는 경우") {
            val result = findTestUseCase.execute(request)

            Then("메서드가 실행되고 결과가 캐시에 저장되어 반환된다") {
                // 데이터베이스가 호출 되었는지 테스트 코드로 파악하는 방법
            }
        }

        When("값이 캐시에 있는 경우") {
            cacheWriter.write("$TEST:${testEntity.id}", "test")

            val result = findTestUseCase.execute(request)

            Then("메서드가 실행되지 않고 캐시에 저장된 결과가 반환된다") {

            }
        }
    }
})
