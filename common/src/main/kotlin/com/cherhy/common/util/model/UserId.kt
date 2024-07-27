package com.cherhy.common.util.model

@JvmInline
value class UserId private constructor(
    val value: Long,
): Comparable<UserId> {
    override fun compareTo(other: UserId): Int {
        return value.compareTo(other.value)
    }
    override fun toString() = value.toString()
    companion object {
        @JvmStatic
        fun of(value: Long) = UserId(value)
    }
}

fun Long.toUserId() = UserId.of(this)