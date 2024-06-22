package com.cherhy.payment.application.port.out

import com.cherhy.payment.domain.TestName

interface RegisterTestPort {
    suspend fun create(
        name: TestName,
    )
}