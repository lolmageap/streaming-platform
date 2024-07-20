package com.cherhy.common.util.model

import com.cherhy.common.util.extension.toSeoulTime
import java.time.ZonedDateTime

@JvmInline
value class EndDateTime(
    val value: ZonedDateTime,
) {
    fun minusDays(
        days: Long,
    ) =
        EndDateTime(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        EndDateTime(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        EndDateTime(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        EndDateTime(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        EndDateTime(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        EndDateTime(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        EndDateTime(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        EndDateTime(
            value.plusYears(years)
        )

    fun toSeoulTime() =
        EndDateTime(
            value.toSeoulTime()
        )

    operator fun compareTo(
        other: ZonedDateTime,
    ) = value.compareTo(other)

    operator fun compareTo(
        other: EndDateTime,
    ) = value.compareTo(other.value)

    operator fun compareTo(
        other: StartDateTime,
    ) = value.compareTo(other.value)

    companion object {
        fun now() =
            EndDateTime(
                ZonedDateTime.now()
            )

        fun of(
            value: ZonedDateTime,
        ) = EndDateTime(value)
    }
}