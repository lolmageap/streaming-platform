package com.cherhy.payment.application.service

import com.cherhy.common.util.CacheConstant.TEST
import com.cherhy.payment.annotation.UseCase
import com.cherhy.payment.application.port.`in`.DeleteTestCommand
import com.cherhy.payment.application.port.`in`.DeleteTestUseCase
import com.cherhy.payment.application.port.out.DeleteTestPort
import org.springframework.cache.annotation.CacheEvict
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class DeleteTestService(
    private val deleteTestPort: DeleteTestPort,
): DeleteTestUseCase {

    @CacheEvict(TEST, key = "#command.id")
    override suspend fun execute(
        command: DeleteTestCommand,
    ) {
        deleteTestPort.delete(
            command.id,
        )
    }
}