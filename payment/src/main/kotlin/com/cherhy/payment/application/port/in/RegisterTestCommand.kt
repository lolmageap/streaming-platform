package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.util.SelfValidating
import com.cherhy.payment.domain.TestName
import jakarta.validation.constraints.NotBlank

data class RegisterTestCommand(
    @field:NotBlank
    val name: TestName,
) : SelfValidating<RegisterTestCommand>()