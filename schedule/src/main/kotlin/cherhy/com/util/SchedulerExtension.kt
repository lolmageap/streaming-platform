package cherhy.com.util

import cherhy.com.util.constant.TimeToLong.ONE_DAY
import cherhy.com.util.constant.TimeToLong.ONE_SECOND
import kotlinx.coroutines.*
import java.time.ZonedDateTime

fun <T> schedule(
    cron: String,
    block: suspend () -> T,
) =
    CoroutineScope(Dispatchers.Default).launch {
        while (isActive) {
            val now = ZonedDateTime.now()
            if (cron.match(now)) {
                launch { block() }
                launch { delay(ONE_DAY) }
            } else {
                delay(ONE_SECOND)
            }
        }
    }

fun String.match(
    now: ZonedDateTime,
): Boolean {
    val cronParts = this.split(" ")
    if (cronParts.size != 6) throw IllegalArgumentException("Invalid cron expression")

    val (second, minute, hour, dayOfMonth, month, dayOfWeek) = cronParts

    val secondMatch = second == "*" || second.toInt() == now.second
    val minuteMatch = minute == "*" || minute.toInt() == now.minute
    val hourMatch = hour == "*" || hour.toInt() == now.hour
    val dayOfMonthMatch = dayOfMonth == "*" || dayOfMonth.toInt() == now.dayOfMonth
    val monthMatch = month == "*" || month.toInt() == now.monthValue
    val dayOfWeekMatch = dayOfWeek == "*" || dayOfWeek.toInt() == now.dayOfWeek.value

    return secondMatch && minuteMatch && hourMatch && dayOfMonthMatch && monthMatch && dayOfWeekMatch
}

private operator fun <E> List<E>.component6() = this[5].toString()