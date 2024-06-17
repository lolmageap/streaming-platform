package com.cherhy.example.application.port.`in`

import com.cherhy.common.annotation.SelfValidating
import com.cherhy.common.annotation.isNumber
import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus
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