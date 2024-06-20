package com.cherhy.example.application.saga

import com.cherhy.example.domain.TestName

data class RegisterTestEvent(
    val name: TestName,
)