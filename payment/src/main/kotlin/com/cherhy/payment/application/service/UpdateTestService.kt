package com.cherhy.payment.application.service

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.payment.annotation.UseCase
import com.cherhy.payment.application.port.`in`.UpdateTestCommand
import com.cherhy.payment.application.port.`in`.UpdateTestUseCase
import com.cherhy.payment.application.port.out.UpdateTestPort
import org.springframework.cache.annotation.CacheEvict
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class UpdateTestService(
    private val updateTestPort: UpdateTestPort,
): UpdateTestUseCase {

    @CacheEvict(TEST, key = "#command.id")
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