package com.cherhy.common.util.model

import java.math.BigDecimal

@JvmInline
value class Price(
    val value: BigDecimal,
) {
    companion object {
        @JvmStatic
        fun of(
            value: BigDecimal,
        ) = Price(value)
    }
}