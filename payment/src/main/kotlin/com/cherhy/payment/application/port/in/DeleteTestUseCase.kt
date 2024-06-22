package com.cherhy.payment.application.port.`in`

interface DeleteTestUseCase {
    suspend fun execute(command: DeleteTestCommand)
}