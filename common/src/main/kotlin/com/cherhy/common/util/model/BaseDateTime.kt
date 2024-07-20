package com.cherhy.common.util.model

import com.cherhy.common.util.extension.toSeoulTime
import java.time.ZonedDateTime

@JvmInline
value class BaseDateTime(
    val value: ZonedDateTime,
) {
    fun minusDays(
        days: Long,
    ) =
        BaseDateTime(
            value.minusDays(days)
        )

    fun minusWeeks(
        weeks: Long,
    ) =
        BaseDateTime(
            value.minusWeeks(weeks)
        )

    fun minusMonths(
        months: Long,
    ) =
        BaseDateTime(
            value.minusMonths(months)
        )

    fun minusYears(
        years: Long,
    ) =
        BaseDateTime(
            value.minusYears(years)
        )

    fun plusDays(
        days: Long,
    ) =
        BaseDateTime(
            value.plusDays(days)
        )

    fun plusWeeks(
        weeks: Long,
    ) =
        BaseDateTime(
            value.plusWeeks(weeks)
        )

    fun plusMonths(
        months: Long,
    ) =
        BaseDateTime(
            value.plusMonths(months)
        )

    fun plusYears(
        years: Long,
    ) =
        BaseDateTime(
            value.plusYears(years)
        )

    fun toSeoulTime() =
        BaseDateTime(
            value.toSeoulTime()
        )

    operator fun compareTo(
        other: ZonedDateTime,
    ) = value.compareTo(other)

    operator fun compareTo(
        other: BaseDateTime,
    ) = value.compareTo(other.value)

    companion object {
        fun now() =
            BaseDateTime(
                ZonedDateTime.now()
            )

        fun of(
            value: ZonedDateTime,
        ) = BaseDateTime(value)
    }
}