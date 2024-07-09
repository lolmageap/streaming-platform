package com.cherhy.payment.adapter.`in`.web

import com.cherhy.payment.annotation.WebAdapter
import com.cherhy.common.util.Payment.Test.UPDATE_TEST
import com.cherhy.payment.application.port.`in`.UpdateTestCommand
import com.cherhy.payment.application.port.`in`.UpdateTestUseCase
import com.cherhy.payment.domain.TestId
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class UpdateTestController(
    private val updateTestUseCase: UpdateTestUseCase,
) {
    @PutMapping(UPDATE_TEST)
    suspend fun updateTestExample(
        @PathVariable id: TestId,
        @RequestBody request: UpdateTestRequest,
    ) = coroutineScope {
        val command = UpdateTestCommand(
            id = id,
            name = request.name,
            status = request.status,
        )

        updateTestUseCase.execute(command)
    }
}