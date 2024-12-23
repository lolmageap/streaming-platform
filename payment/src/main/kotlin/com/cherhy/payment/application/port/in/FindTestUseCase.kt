package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.domain.TestDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FindTestUseCase {
    suspend fun execute(
        command: FindTestCommand,
    ): TestDomain

    suspend fun execute(
        command: FindAllTestCommand,
        pageable: Pageable,
    ): Page<TestDomain>

    suspend fun executeWithCacheLock(
        command: FindTestCommand,
    ): TestDomain
}