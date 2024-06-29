package com.cherhy.payment.application.port.`in`

import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus

fun FindTestCommand.Companion.of(
    id: TestId,
) = FindTestCommand(
    id = id,
)

fun FindAllTestCommand.Companion.of(
    name: TestName?,
    status: TestStatus?,
) = FindAllTestCommand(
    name = name,
    status = status,
)