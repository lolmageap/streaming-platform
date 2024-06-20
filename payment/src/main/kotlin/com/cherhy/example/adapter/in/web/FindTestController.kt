package com.cherhy.example.adapter.`in`.web

import com.cherhy.common.annotation.WebAdapter
import com.cherhy.example.application.port.`in`.FindTestCommand
import com.cherhy.example.application.port.`in`.FindTestUseCase
import com.cherhy.example.domain.TestId
import com.cherhy.example.util.constant.EndPoint.Test.GET_TEST
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindTestController(
    private val findTestUseCase: FindTestUseCase,
) {
    @GetMapping(GET_TEST)
    suspend fun getTestExample(
        @PathVariable id: TestId,
    ) = coroutineScope {
        val command = FindTestCommand(id)
        findTestUseCase.execute(command)
    }

    // TODO: IMPLEMENT GET TESTS ENDPOINT !!!
}