package com.cherhy.payment.adapter.out.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

interface TestCoroutineRepository : CoroutineCrudRepository<TestR2dbcEntity, Long>, TestRepositoryCustom

interface TestRepositoryCustom {
    suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable,
    ): Page<TestR2dbcEntity>
}

@Repository
class TestRepositoryCustomImpl : TestRepositoryCustom {
    override suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable
    ): Page<TestR2dbcEntity> {
        TODO("Not yet implemented")
//        return sessionFactory.pageQuery(pageable) {
//            select(entity(TestR2dbcEntity::class))
//            from(entity(TestR2dbcEntity::class))
//            whereAnd(
//                name?.let {
//                    col(TestR2dbcEntity::name).equal(name)
//                },
//                status?.let {
//                    col(TestR2dbcEntity::status).equal(status)
//                },
//            )
//        }
    }
}