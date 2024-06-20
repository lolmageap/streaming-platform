package com.cherhy.example.adapter.`in`.web

import com.cherhy.common.annotation.WebAdapter
import com.cherhy.example.application.port.`in`.DeleteTestCommand
import com.cherhy.example.application.port.`in`.DeleteTestUseCase
import com.cherhy.example.domain.TestId
import com.cherhy.example.util.constant.EndPoint.Test.DELETE_TEST
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