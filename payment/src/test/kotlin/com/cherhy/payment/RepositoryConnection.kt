package com.cherhy.payment

import com.cherhy.payment.adapter.out.persistence.TestCoroutineRepository
import com.cherhy.payment.util.WithTestContainers
import com.kmsl.dsl.clazz.field
import com.kmsl.dsl.extension.document
import com.kmsl.dsl.extension.findOne
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate

@SpringBootTest
class RepositoryConnection(
    private val testRepository: TestCoroutineRepository,
    private val mongoTemplate: MongoTemplate,
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

    Given("Collection을 생성해서 ") {
        val testEntity = EntityFactory.generateTestMongoEntity()

        When("저장하고 ") {
            mongoTemplate.save(testEntity)

            Then("조회하면 저장한 Entity가 반환된다") {
                val document = document {
                    field(TestMongoEntity::id) eq testEntity.id
                }

                val result = mongoTemplate.findOne(document, TestMongoEntity::class)!!
                result.name shouldBe testEntity.name
                result.status shouldBe testEntity.status
            }
        }
    }
})