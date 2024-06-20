package com.cherhy.example.application.service

import com.cherhy.common.annotation.UseCase
import com.cherhy.example.application.port.`in`.UpdateTestCommand
import com.cherhy.example.application.port.`in`.UpdateTestUseCase
import com.cherhy.example.application.port.out.UpdateTestPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class UpdateTestService(
    private val updateTestPort: UpdateTestPort,
): UpdateTestUseCase {
    override suspend fun execute(
        command: UpdateTestCommand,
    ) {
        updateTestPort.update(
            command.id,
            command.name,
            command.status,
        )
    }
}