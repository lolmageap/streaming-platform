package com.cherhy.util.model

@JvmInline
value class Bucket(
    val value: String,
) {
    companion object {
        fun of(
            value: String,
        ) = Bucket(value)
    }
}