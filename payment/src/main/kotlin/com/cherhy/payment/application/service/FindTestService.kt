package com.cherhy.payment.application.service

import com.cherhy.payment.annotation.UseCase
import com.cherhy.payment.application.port.`in`.FindAllTestCommand
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.application.port.out.GetTestPort
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
internal class FindTestService(
    private val getTestPort: GetTestPort,
): FindTestUseCase {
    override suspend fun execute(
        command: FindTestCommand,
    ) =
        getTestPort.get(
            command.id,
        )

    override suspend fun execute(
        command: FindAllTestCommand,
        pageable: Pageable,
    ) =
        getTestPort.get(
            name = command.name,
            status = command.status,
            pageable = pageable,
        )
}