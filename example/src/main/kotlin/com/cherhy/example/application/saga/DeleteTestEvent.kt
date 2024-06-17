package com.cherhy.example.application.saga

import com.cherhy.example.domain.TestId

data class DeleteTestEvent(
    val id: TestId,
)
