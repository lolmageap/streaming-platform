package com.cherhy.payment.adapter.out.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("test")
data class TestR2dbcEntity(
    val name: String,
    val status: String,
    @Id val id: Long = 0
)