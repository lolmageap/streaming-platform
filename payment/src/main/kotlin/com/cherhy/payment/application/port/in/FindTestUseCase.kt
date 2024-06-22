package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.domain.TestDomain

interface FindTestUseCase {
    suspend fun execute(
        command: FindTestCommand,
    ): TestDomain
}