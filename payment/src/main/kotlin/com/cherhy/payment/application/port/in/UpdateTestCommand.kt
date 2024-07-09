package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.util.SelfValidating
import com.cherhy.common.util.extension.isNumber
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateTestCommand(
    @field:NotNull
    @field:TargetAggregateIdentifier
    val id: TestId,

    @field:NotNull
    val name: TestName,

    @field:NotNull
    val status: TestStatus,
) : SelfValidating<UpdateTestCommand>() {
    init {
        require(id.value.isNumber()) { "Id must be a number" }
    }
}