package com.cherhy.example.application.port.`in`

interface RegisterTestUseCase {
    suspend fun execute(
        command: RegisterTestCommand,
    )
}