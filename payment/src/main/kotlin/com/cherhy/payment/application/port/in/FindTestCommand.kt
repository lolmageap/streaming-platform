package com.cherhy.payment.application.port.`in`

import com.cherhy.common.annotation.SelfValidating
import com.cherhy.common.annotation.isNumber
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus
import jakarta.validation.constraints.NotNull

data class FindTestCommand internal constructor(
    @field:NotNull
    val id: TestId,
) : SelfValidating<FindTestCommand>() {
    init {
        require(id.value.isNumber()) { "Id must be a number" }
    }

    companion object
}

data class FindAllTestCommand internal constructor(
    val name: TestName?,
    val status: TestStatus?,
) : SelfValidating<FindAllTestCommand>() {
    init {
        require(name?.value?.contains("@") ?: true) { "이메일 양식을 맞춰주세요." }
    }

    companion object
}
