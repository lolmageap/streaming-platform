package com.cherhy.common.util.extension

import com.cherhy.common.util.SEOUL
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

fun ZonedDateTime.toSeoulTime(): ZonedDateTime =
    when(this.zone) {
        UTC -> this.plusHours(9)
        SEOUL -> this
        else -> this.withZoneSameInstant(SEOUL)
    }

fun ZonedDateTime.toUtcTime(): ZonedDateTime =
    when(this.zone) {
        UTC -> this
        SEOUL -> this.minusHours(9)
        else -> this.withZoneSameInstant(UTC)
    }

fun LocalDateTime.toZonedDateTime(
    zoneOffset: ZoneOffset = UTC,
) = ZonedDateTime.of(this, zoneOffset)!!