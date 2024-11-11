package com.cherhy.common.util.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun <T, R> Map<T, Iterable<R>>.countAll(
    predicate: (R) -> Boolean = { true },
) =
    this.values.flatten().count(predicate)

fun <T, R> Map<T, Iterable<R>>.sumAll(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.values.flatten().sumOf(bigDecimalSelector)

fun <T, R> Map<T, Iterable<R>>.averageAll(
    scale: Int = 0,
    roundingMode: RoundingMode = RoundingMode.UNNECESSARY,
    bigDecimalSelector: (R) -> BigDecimal,
): BigDecimal {
    val count = this.values.flatten().count().toBigDecimal()
    val sumValues= this.values.flatten().sumOf(bigDecimalSelector)
    return sumValues.divide(count, scale, roundingMode)
}

fun <T, R> Map<T, Iterable<R>>.maxAll(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.values.flatten().maxOf(bigDecimalSelector)

fun <T, R> Map<T, Iterable<R>>.minAll(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.values.flatten().minOf(bigDecimalSelector)

fun <T, R> Map<T, Iterable<R>>.sumOf(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.map { (key, value) ->
        key to value.sumOf(bigDecimalSelector)
    }.toMap()

fun <T, R> Map<T, Iterable<R>>.countOf(
    predicate: (R) -> Boolean = { true },
) =
    this.map { (key, value) ->
        val filteredValue = value.filter(predicate)
        key to filteredValue.count()
    }.toMap()

fun <T, R> Map<T, Iterable<R>>.averageOf(
    scale: Int = 0,
    roundingMode: RoundingMode,
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.map { (key, value) ->
        val count = value.count().toBigDecimal()
        val sumValues = value.sumOf(bigDecimalSelector)
        key to sumValues.divide(count, scale, roundingMode)
    }.toMap()

fun <T, R> Map<T, Iterable<R>>.maxOf(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.map { (key, value) ->
        key to value.maxOf(bigDecimalSelector)
    }.toMap()

fun <T, R> Map<T, Iterable<R>>.minOf(
    bigDecimalSelector: (R) -> BigDecimal,
) =
    this.map { (key, value) ->
        key to value.minOf(bigDecimalSelector)
    }.toMap()