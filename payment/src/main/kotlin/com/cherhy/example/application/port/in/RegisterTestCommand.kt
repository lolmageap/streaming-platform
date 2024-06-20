package com.cherhy.example.application.port.`in`

import com.cherhy.common.annotation.SelfValidating
import com.cherhy.example.domain.TestName
import jakarta.validation.constraints.NotBlank

data class RegisterTestCommand(
    @field:NotBlank
    val name: TestName,
) : SelfValidating<RegisterTestCommand>()