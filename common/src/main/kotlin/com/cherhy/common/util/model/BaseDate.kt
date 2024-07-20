package com.cherhy.common.util.model

import java.time.LocalDate

@JvmInline
value class BaseDate(
    val value: LocalDate,
) {
    fun minusDays(
        days: Long,
    ) =
        BaseDate(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        BaseDate(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        BaseDate(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        BaseDate(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        BaseDate(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        BaseDate(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        BaseDate(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        BaseDate(
            value.plusYears(years)
        )

    operator fun compareTo(
        other: BaseDate,
    ) = value.compareTo(other.value)

    operator fun compareTo(
        other: LocalDate,
    ) = value.compareTo(other)

    fun toEndDate() = EndDate(value)

    fun toStartDate() = StartDate(value)

    companion object {
        fun now() =
            BaseDate(
                LocalDate.now()
            )

        fun of(
            year: Int,
            month: Int,
            dayOfMonth: Int,
        ) =
            BaseDate(
                LocalDate.of(year, month, dayOfMonth)
            )

        fun of(
            value: LocalDate,
        ) = BaseDate(value)
    }
}