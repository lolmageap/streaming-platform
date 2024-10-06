package com.cherhy.payment.domain

import java.io.Serializable

data class TestDomain(
    val id: TestId,
    val name: TestName,
    val status: TestStatus,
): Serializable {
    companion object {
        @JvmStatic
        fun generateTestDomain(
            id: TestId,
            name: TestName,
            status: TestStatus,
        ) =
            TestDomain(
                id = id,
                name = name,
                status = status,
            )
    }
}

@JvmInline
value class TestId(
    val value: String,
)

@JvmInline
value class TestName(
    val value: String,
)

@JvmInline
value class TestStatus(
    val value: Status,
) {
    companion object {
        @JvmStatic
        fun fromString(value: String) =
            TestStatus(
                Status.valueOf(value)
            )
    }
}

enum class Status {
    ACTIVE,
    INACTIVE,
}