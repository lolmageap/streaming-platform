package com.cherhy.payment.application.saga

import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus

data class UpdateTestEvent(
    val id: TestId,
    val name: TestName,
    val status: TestStatus,
)