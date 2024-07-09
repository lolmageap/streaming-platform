package com.cherhy.common.util.extension

fun String.toSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
}

fun String.isNumber(): Boolean {
    return this.matches(Regex("\\d+"))
}
