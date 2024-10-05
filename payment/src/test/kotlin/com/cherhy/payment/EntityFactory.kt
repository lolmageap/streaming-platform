package com.cherhy.payment

import com.cherhy.payment.adapter.out.persistence.TestR2dbcEntity

object EntityFactory {
    fun generateTestR2dbcEntity(
        name: String = "test",
        status: String = "ACTIVE",
    ) =
        TestR2dbcEntity(
            name = name,
            status = status,
        )
}