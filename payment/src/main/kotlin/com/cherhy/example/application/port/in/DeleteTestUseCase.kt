package com.cherhy.example.application.port.`in`

interface DeleteTestUseCase {
    suspend fun execute(command: DeleteTestCommand)
}