package com.cherhy.example.application.service

import com.cherhy.common.annotation.UseCase
import com.cherhy.example.application.port.`in`.DeleteTestCommand
import com.cherhy.example.application.port.`in`.DeleteTestUseCase
import com.cherhy.example.application.port.out.DeleteTestPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class DeleteTestService(
    private val deleteTestPort: DeleteTestPort,
): DeleteTestUseCase {
    override suspend fun execute(
        command: DeleteTestCommand,
    ) {
        deleteTestPort.delete(
            command.id,
        )
    }
}