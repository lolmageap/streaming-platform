package com.cherhy.payment

import com.cherhy.payment.adapter.out.persistence.TestCoroutineRepository
import com.cherhy.payment.util.WithTestContainers
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RepositoryConnectionTest(
    private val testRepository: TestCoroutineRepository,
) : WithTestContainers, BehaviorSpec({

    Given("Entity를 생성해서 ") {
        val testEntity = EntityFactory.generateTestR2dbcEntity()

        When("저장하고 ") {
            testRepository.save(testEntity)

            Then("조회하면 저장한 Entity가 반환된다") {
                val result = testRepository.findById(testEntity.id)!!
                result.name shouldBe testEntity.name
                result.status shouldBe testEntity.status
            }
        }
    }
})