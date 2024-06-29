package com.cherhy.payment.adapter.`in`.web

import com.cherhy.common.annotation.WebAdapter
import com.cherhy.common.util.Payment.Test.REGISTER_TEST
import com.cherhy.payment.application.port.`in`.RegisterTestCommand
import com.cherhy.payment.application.port.`in`.RegisterTestUseCase
import kotlinx.coroutines.coroutineScope
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterTestController(
    private val registerTestUseCase: RegisterTestUseCase,
) {
    @ResponseStatus(CREATED)
    @PostMapping(REGISTER_TEST)
    suspend fun createTestExample(
        request: RegisterTestRequest,
    ) = coroutineScope {
        val command = RegisterTestCommand(request.name)
        registerTestUseCase.execute(command)
    }
}