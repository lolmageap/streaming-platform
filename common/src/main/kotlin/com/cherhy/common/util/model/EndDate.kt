package com.cherhy.common.util.model

import java.time.LocalDate

@JvmInline
value class EndDate(
    val value: LocalDate,
) {
    fun minusDays(
        days: Long,
    ) =
        EndDate(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        EndDate(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        EndDate(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        EndDate(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        EndDate(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        EndDate(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        EndDate(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        EndDate(
            value.plusYears(years)
        )

    operator fun compareTo(
        other: LocalDate,
    ) = value.compareTo(other)

    operator fun compareTo(
        other: EndDate,
    ) = value.compareTo(other.value)

    operator fun compareTo(
        other: StartDate,
    ) = value.compareTo(other.value)

    companion object {
        fun now() =
            EndDate(
                LocalDate.now()
            )

        fun of(
            year: Int,
            month: Int,
            dayOfMonth: Int,
        ) =
            EndDate(
                LocalDate.of(year, month, dayOfMonth)
            )

        fun of(
            value: LocalDate,
        ) = EndDate(value)
    }
}