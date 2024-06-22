package com.cherhy.payment.application.port.`in`

import com.cherhy.common.annotation.SelfValidating
import com.cherhy.common.annotation.isNumber
import com.cherhy.payment.domain.TestId
import jakarta.validation.constraints.NotNull

data class FindTestCommand(
    @field:NotNull
    val id: TestId,
) : SelfValidating<FindTestCommand>() {
    init {
        require(id.value.isNumber()) { "Id must be a number" }
    }
}