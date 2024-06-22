package com.cherhy.payment.application.port.`in`

interface RegisterTestUseCase {
    suspend fun execute(
        command: RegisterTestCommand,
    )
}