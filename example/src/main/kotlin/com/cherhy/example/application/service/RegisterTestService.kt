package com.cherhy.example.application.service

import com.cherhy.common.annotation.UseCase
import com.cherhy.example.application.port.`in`.RegisterTestCommand
import com.cherhy.example.application.port.`in`.RegisterTestUseCase
import com.cherhy.example.application.port.out.RegisterTestPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterTestService(
    private val registerTestPort: RegisterTestPort,
): RegisterTestUseCase {
    override suspend fun execute(
        command: RegisterTestCommand,
    ) {
        registerTestPort.create(
            command.name,
        )
    }
}