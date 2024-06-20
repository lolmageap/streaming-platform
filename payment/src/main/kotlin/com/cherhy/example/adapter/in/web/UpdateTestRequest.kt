package com.cherhy.example.adapter.`in`.web

import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus

data class UpdateTestRequest(
    val name: TestName,
    val status: TestStatus,
)