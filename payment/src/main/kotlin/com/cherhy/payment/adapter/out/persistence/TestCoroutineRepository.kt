package com.cherhy.payment.adapter.out.persistence

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TestCoroutineRepository: CoroutineCrudRepository<TestR2dbcEntity, Long> {
    suspend fun findAllByNameAndStatus(
        name: String,
        status: String,
        pageable: Pageable,
    ): Flow<TestR2dbcEntity>

    suspend fun countByNameAndStatus(
        name: String,
        status: String,
    ): Long
}