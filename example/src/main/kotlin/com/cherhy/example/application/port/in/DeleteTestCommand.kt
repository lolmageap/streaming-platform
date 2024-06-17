package com.cherhy.example.application.port.`in`

import com.cherhy.example.domain.TestId

data class DeleteTestCommand(
    val id: TestId,
)