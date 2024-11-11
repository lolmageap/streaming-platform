package com.cherhy.common.util.extension

import java.math.BigDecimal

val Any?.noReturn: Unit
    get() = Unit

fun Any.toBigDecimal(): BigDecimal =
    this.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO

fun Any?.toInt(): Int =
    this?.toString()?.substringBefore(".")?.toIntOrNull() ?: 0

fun Any?.toLong(): Long =
    this?.toString()?.substringBefore(".")?.toLongOrNull() ?: 0L