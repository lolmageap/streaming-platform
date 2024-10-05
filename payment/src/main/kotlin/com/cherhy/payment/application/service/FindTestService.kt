package com.cherhy.payment.application.service

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.payment.annotation.UseCase
import com.cherhy.payment.application.port.`in`.FindAllTestCommand
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.application.port.out.GetTestPort
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
internal class FindTestService(
    private val getTestPort: GetTestPort,
): FindTestUseCase {

    @Cacheable(TEST, key = "#command.id")
    override suspend fun execute(
        command: FindTestCommand,
    ) =
        getTestPort.get(
            command.id,
        )

    @Cacheable(TEST)
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