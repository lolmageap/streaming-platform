package com.cherhy.payment.application.port.out

import com.cherhy.payment.domain.TestDomain
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetTestPort {
    suspend fun get(
        id: TestId,
    ): TestDomain

    suspend fun get(
        name: TestName?,
        status: TestStatus?,
        pageable: Pageable,
    ): Page<TestDomain>
}