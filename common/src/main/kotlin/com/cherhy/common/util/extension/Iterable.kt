package com.cherhy.common.util.extension

fun <T> Iterable<T>.isNotContains(
    value: T
) =
    !this.contains(value)