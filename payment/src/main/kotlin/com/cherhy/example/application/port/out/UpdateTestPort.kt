package com.cherhy.example.application.port.out

import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus

interface UpdateTestPort {
    suspend fun update(
        id: TestId,
        name: TestName,
        status: TestStatus,
    )
}