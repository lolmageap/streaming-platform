package com.cherhy.payment.application.port.out

import com.cherhy.payment.domain.TestId

interface DeleteTestPort {
    suspend fun delete(
        id: TestId,
    )
}