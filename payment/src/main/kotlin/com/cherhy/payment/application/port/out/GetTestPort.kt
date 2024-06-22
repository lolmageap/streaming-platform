package com.cherhy.payment.application.port.out

import com.cherhy.payment.domain.TestDomain
import com.cherhy.payment.domain.TestId

interface GetTestPort {
    suspend fun get(
        id: TestId,
    ): TestDomain
}