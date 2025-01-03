package com.cherhy.payment

import com.cherhy.payment.adapter.out.persistence.TestR2dbcEntity

object EntityFactory {
    fun generateTestR2dbcEntity(
        id: Long = 0,
        name: String = "test",
        status: String = "ACTIVE",
    ) =
        TestR2dbcEntity(
            id = id,
            name = name,
            status = status,
        )

    fun generateTestMongoEntity(
        id: String = "test",
        name: String = "test",
        status: String = "ACTIVE",
    ) =
        TestMongoEntity(
            id = id,
            name = name,
            status = status,
        )
}

data class TestMongoEntity(
    val id: String,
    val name: String,
    val status: String,
)