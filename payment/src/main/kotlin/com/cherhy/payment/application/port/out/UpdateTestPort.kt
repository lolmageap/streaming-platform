package com.cherhy.payment.application.port.out

import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus

interface UpdateTestPort {
    suspend fun update(
        id: TestId,
        name: TestName,
        status: TestStatus,
    )
}