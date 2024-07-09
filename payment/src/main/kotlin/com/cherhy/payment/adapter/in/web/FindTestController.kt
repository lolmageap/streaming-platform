package com.cherhy.payment.adapter.`in`.web

import com.cherhy.payment.annotation.WebAdapter
import com.cherhy.common.util.Payment.Test.GET_TEST
import com.cherhy.common.util.Payment.Test.GET_TESTS
import com.cherhy.payment.application.port.`in`.FindAllTestCommand
import com.cherhy.payment.application.port.`in`.FindTestCommand
import com.cherhy.payment.application.port.`in`.FindTestUseCase
import com.cherhy.payment.application.port.`in`.of
import com.cherhy.payment.domain.TestId
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
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

    @GetMapping(GET_TESTS)
    suspend fun getTestExamples(
        @ModelAttribute request: GetTestRequest,
        @PageableDefault(page = 0, size = 10) pageable: Pageable,
    ) = coroutineScope {
        val command = FindAllTestCommand.of(
            name = request.name,
            status = request.status,
        )
        findTestUseCase.execute(command, pageable)
    }
}