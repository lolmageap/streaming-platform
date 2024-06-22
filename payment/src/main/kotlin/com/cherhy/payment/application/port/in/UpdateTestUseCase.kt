package com.cherhy.payment.application.port.`in`

interface UpdateTestUseCase {
    suspend fun execute(
        command: UpdateTestCommand,
    )
}