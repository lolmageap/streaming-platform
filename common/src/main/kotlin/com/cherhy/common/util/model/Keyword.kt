package com.cherhy.common.util.model

@JvmInline
value class Keyword(
    val value: String,
) {
    companion object {
        fun of(
            value: String
        ) = Keyword(value)
    }
}