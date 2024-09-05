package com.cherhy.payment.adapter.out.persistence

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.pageQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TestCoroutineRepository : CoroutineCrudRepository<TestR2dbcEntity, Long>, TestRepositoryCustom {
}

interface TestRepositoryCustom {
    suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable,
    ): Page<TestR2dbcEntity>
}

class TestRepositoryCustomImpl(
    private val sessionFactory: SpringDataQueryFactory,
) : TestRepositoryCustom {
    override suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable
    ): Page<TestR2dbcEntity> {
        return sessionFactory.pageQuery(pageable) {
            select(entity(TestR2dbcEntity::class))
            from(entity(TestR2dbcEntity::class))
            whereAnd(
                name?.let {
                    col(TestR2dbcEntity::name).equal(name)
                },
                status?.let {
                    col(TestR2dbcEntity::status).equal(status)
                },
            )
        }
    }
}