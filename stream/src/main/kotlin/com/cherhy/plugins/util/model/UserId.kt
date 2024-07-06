package com.cherhy.plugins.util.model

// TODO: common module로 올리자.
@JvmInline
value class UserId(
    val value: Long,
) {
    override fun toString() = value.toString()
    companion object {
        @JvmStatic
        fun of(
            value: Long,
        ) = UserId(value)
    }
}

fun Long.toUserId() = UserId.of(this)