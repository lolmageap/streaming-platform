package com.cherhy.example.application.saga

import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus

data class UpdateTestEvent(
    val id: TestId,
    val name: TestName,
    val status: TestStatus,
)