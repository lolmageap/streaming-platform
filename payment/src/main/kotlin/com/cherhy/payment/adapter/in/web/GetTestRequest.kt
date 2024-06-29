package com.cherhy.payment.adapter.`in`.web

import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus

data class GetTestRequest(
    val name: TestName?,
    val status: TestStatus?,
)