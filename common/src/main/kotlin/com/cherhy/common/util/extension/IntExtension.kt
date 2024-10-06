package com.cherhy.common.util.extension

import java.time.Duration

fun Int.toSeconds(): Duration = Duration.ofSeconds(this.toLong())