package com.cherhy.example.application.service

import com.cherhy.common.annotation.UseCase
import com.cherhy.example.application.port.`in`.FindTestCommand
import com.cherhy.example.application.port.`in`.FindTestUseCase
import com.cherhy.example.application.port.out.GetTestPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class FindTestService(
    private val getTestPort: GetTestPort,
): FindTestUseCase {
    override suspend fun execute(
        command: FindTestCommand,
    ) =
        getTestPort.get(
            command.id,
        )
}