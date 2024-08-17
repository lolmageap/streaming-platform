package com.cherhy.common.util.extension

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

fun ZonedDateTime.toSeoulTime() = this.withZoneSameInstant(ZoneId.of("Asia/Seoul"))!!
fun ZonedDateTime.toUtcTime() = this.withZoneSameInstant(ZoneId.of("UTC"))!!
fun LocalDateTime.toZonedDateTime(zoneOffset: ZoneOffset = UTC) = ZonedDateTime.of(this, zoneOffset)!!