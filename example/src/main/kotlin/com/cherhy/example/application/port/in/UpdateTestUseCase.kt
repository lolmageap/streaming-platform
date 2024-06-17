package com.cherhy.example.application.port.`in`

interface UpdateTestUseCase {
    suspend fun execute(
        command: UpdateTestCommand,
    )
}