package com.cherhy.example.application.port.out

import com.cherhy.example.domain.TestName

interface RegisterTestPort {
    suspend fun create(
        name: TestName,
    )
}