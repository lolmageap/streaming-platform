package com.cherhy.common.util.model

import java.time.LocalDate

@JvmInline
value class StartDate(
    val value: LocalDate,
) {
    fun minusDays(
        days: Long,
    ) =
        StartDate(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        StartDate(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        StartDate(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        StartDate(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        StartDate(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        StartDate(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        StartDate(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        StartDate(
            value.plusYears(years)
        )

    operator fun compareTo(
        other: LocalDate,
    ) = value.compareTo(other)

    operator fun compareTo(
        other: StartDate,
    ) = value.compareTo(other.value)

    operator fun compareTo(
        other: EndDate,
    ) = value.compareTo(other.value)

    companion object {
        fun now() =
            StartDate(
                LocalDate.now()
            )

        fun of(
            year: Int,
            month: Int,
            dayOfMonth: Int,
        ) =
            StartDate(
                LocalDate.of(year, month, dayOfMonth)
            )

        fun of(
            value: LocalDate,
        ) = StartDate(value)
    }
}