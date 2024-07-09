package com.cherhy.payment.adapter.`in`.web

import com.cherhy.payment.annotation.WebAdapter
import com.cherhy.common.util.Payment.Test.DELETE_TEST
import com.cherhy.payment.application.port.`in`.DeleteTestCommand
import com.cherhy.payment.application.port.`in`.DeleteTestUseCase
import com.cherhy.payment.domain.TestId
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class DeleteTestController(
    private val deleteTestUseCase: DeleteTestUseCase,
) {
    @DeleteMapping(DELETE_TEST)
    suspend fun deleteTestExample(
        @PathVariable id: TestId,
    ) = coroutineScope {
        val command = DeleteTestCommand(id)
        deleteTestUseCase.execute(command)
    }
}