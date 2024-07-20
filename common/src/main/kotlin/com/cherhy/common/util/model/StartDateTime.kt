package com.cherhy.common.util.model

import com.cherhy.common.util.extension.toSeoulTime
import java.time.ZonedDateTime

@JvmInline
value class StartDateTime(
    val value: ZonedDateTime,
) {
    fun minusDays(
        days: Long,
    ) =
        StartDateTime(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        StartDateTime(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        StartDateTime(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        StartDateTime(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        StartDateTime(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        StartDateTime(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        StartDateTime(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        StartDateTime(
            value.plusYears(years)
        )

    fun toSeoulTime() =
        StartDateTime(
            value.toSeoulTime()
        )

    operator fun compareTo(
        other: ZonedDateTime,
    ) = value.compareTo(other)

    operator fun compareTo(
        other: StartDateTime,
    ) = value.compareTo(other.value)

    operator fun compareTo(
        other: EndDateTime,
    ) = value.compareTo(other.value)

    companion object {
        fun now() =
            StartDateTime(
                ZonedDateTime.now()
            )

        fun of(
            value: ZonedDateTime,
        ) = StartDateTime(value)
    }
}