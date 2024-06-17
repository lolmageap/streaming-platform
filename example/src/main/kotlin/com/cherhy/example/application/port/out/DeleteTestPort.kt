package com.cherhy.example.application.port.out

import com.cherhy.example.domain.TestId

interface DeleteTestPort {
    suspend fun delete(
        id: TestId,
    )
}