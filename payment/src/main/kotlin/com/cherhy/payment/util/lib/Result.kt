package com.cherhy.payment.util.lib

fun <T> Result<T>.onFailAction(
    block: () -> T,
) =
    if (isFailure) block()
    else if (isSuccess) getOrThrow()
    else throw IllegalStateException("Result is not success or failure")