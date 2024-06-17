package com.cherhy.example.adapter.`in`.web

import com.cherhy.common.annotation.WebAdapter
import com.cherhy.example.application.port.`in`.RegisterTestCommand
import com.cherhy.example.application.port.`in`.RegisterTestUseCase
import com.cherhy.example.util.constant.EndPoint.Test.REGISTER_TEST
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