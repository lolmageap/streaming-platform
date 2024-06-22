package com.cherhy.payment.application.saga

import com.cherhy.payment.domain.TestId

data class DeleteTestEvent(
    val id: TestId,
)
