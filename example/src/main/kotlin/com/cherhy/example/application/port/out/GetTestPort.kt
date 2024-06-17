package com.cherhy.example.application.port.out

import com.cherhy.example.domain.TestDomain
import com.cherhy.example.domain.TestId

interface GetTestPort {
    suspend fun get(
        id: TestId,
    ): TestDomain
}