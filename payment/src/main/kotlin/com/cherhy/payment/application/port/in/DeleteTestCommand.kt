package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.domain.TestId

data class DeleteTestCommand(
    val id: TestId,
)