package com.cherhy.payment.application.service

import com.cherhy.payment.annotation.UseCase
import com.cherhy.payment.application.port.`in`.RegisterTestCommand
import com.cherhy.payment.application.port.`in`.RegisterTestUseCase
import com.cherhy.payment.application.port.out.RegisterTestPort
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