package com.cherhy.payment.application.saga

import com.cherhy.payment.domain.TestName

data class RegisterTestEvent private constructor(
    val name: String,
) {
    companion object {
        fun of(
            name: TestName,
        ) = RegisterTestEvent(name.value)
    }
}