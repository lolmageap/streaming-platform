package com.cherhy.payment.application.saga

import com.cherhy.payment.domain.TestName

data class RegisterTestEvent(
    val name: TestName,
)