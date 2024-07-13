package com.cherhy.common.util.model

@JvmInline
value class Page(
    val value: Int,
)

@JvmInline
value class Size(
    val value: Int,
)

@JvmInline
value class Sort(
    val value: String,
)

enum class Direction {
    ASC, DESC,
}

@JvmInline
value class Offset(
    val value: Long,
) {
    companion object {
        fun of(
            page: Page,
            size: Size,
        ) = Offset(
            (page.value - 1) * size.value.toLong()
        )
    }
}