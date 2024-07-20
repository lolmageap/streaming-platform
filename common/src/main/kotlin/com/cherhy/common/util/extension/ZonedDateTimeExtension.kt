package com.cherhy.common.util.extension

import java.time.ZoneOffset
import java.time.ZonedDateTime

fun ZonedDateTime.toSeoulTime() = withZoneSameInstant(ZoneOffset.ofHours(9))!!