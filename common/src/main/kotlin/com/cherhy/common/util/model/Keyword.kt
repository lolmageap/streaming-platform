package com.cherhy.common.util.model

@JvmInline
value class Keyword(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String
        ) = Keyword(value)
    }
}