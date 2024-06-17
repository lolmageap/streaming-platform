package com.cherhy.example.application.port.`in`

import com.cherhy.example.domain.TestDomain

interface FindTestUseCase {
    suspend fun execute(
        command: FindTestCommand,
    ): TestDomain
}