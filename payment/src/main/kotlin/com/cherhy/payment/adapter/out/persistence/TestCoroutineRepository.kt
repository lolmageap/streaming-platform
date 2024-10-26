package com.cherhy.payment.adapter.out.persistence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.domain.Pageable
import org.springframework.data.mapping.toDotPath
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.ReactiveSelectOperation
import org.springframework.data.r2dbc.core.select
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import kotlin.reflect.KProperty

interface TestCoroutineRepository : CoroutineCrudRepository<TestR2dbcEntity, Long>, TestRepositoryCustom

interface TestRepositoryCustom {
    suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable,
    ): Flow<TestR2dbcEntity>

    suspend fun countAll(
        name: String?,
        status: String?,
    ): Long
}

@Repository
class TestRepositoryCustomImpl(
    private val template: R2dbcEntityTemplate,
) : TestRepositoryCustom {
    override suspend fun findAll(
        name: String?,
        status: String?,
        pageable: Pageable,
    ) =
        template.select<TestR2dbcEntity>()
            .findAll(
                where(TestR2dbcEntity::name)
                    .equalsTo(name)
                    .and(TestR2dbcEntity::status)
                    .equalsTo(status)
                    .toQuery()
            )

    override suspend fun countAll(
        name: String?,
        status: String?,
    ) =
        template.select<TestR2dbcEntity>()
            .count(
                where(TestR2dbcEntity::name)
                    .equalsTo(name)
                    .and(TestR2dbcEntity::status)
                    .equalsTo(status)
                    .toQuery()
            )
}

private suspend fun <T> ReactiveSelectOperation.ReactiveSelect<T>.count(
    toQuery: Query
): Long =
    this.matching(toQuery).count().awaitSingleOrNull()!!

private fun where(
    property: KProperty<*>,
) = Criteria.where(property.toDotPath())

private fun count(
    criteria: Criteria,
) = Query.query(criteria)

private fun Criteria.and(
    property: KProperty<*>,
) = this.and(property.toDotPath())

private fun Criteria.toQuery() = Query.query(this)

private fun <T> Criteria.CriteriaStep.equalsTo(
    value: T?,
) = value?.let { this.`is`(it) }!!

fun <T> ReactiveSelectOperation.ReactiveSelect<T>.findAll(
    predicate: Query,
) = this.matching(predicate).all().toIterable().asFlow()