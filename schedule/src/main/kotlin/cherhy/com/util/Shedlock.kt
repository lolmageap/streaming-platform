package cherhy.com.util

import cherhy.com.plugins.reactiveTransaction
import cherhy.com.util.Shedlocks.pessimisticLock
import com.cherhy.common.util.extension.between
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.vendors.ForUpdateOption.PostgreSQL.MODE.NO_WAIT
import java.time.Duration
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

suspend fun <T> shedlock(
    name: String,
    lockAtMostFor: Duration,
    block: suspend () -> T,
) {
    reactiveTransaction {
        val now = ZonedDateTime.now(UTC)
        try {
            val resultRow = Shedlocks.selectAll()
                .where { Shedlocks.name eq name }
                .pessimisticLock(NO_WAIT)
                .singleOrNull()
                ?: insertNewShedlock(name, lockAtMostFor)

            val shedLock = resultRow.toShedLock()

            if (now between shedLock.lockedAt..shedLock.lockUntil) {
                throw RuntimeException("Already locked")
            }

            Shedlocks.update({ Shedlocks.name eq name }) {
                val updatedAt = ZonedDateTime.now(UTC)
                it[lockedAt] = updatedAt.toLocalDateTime()
                it[lockUntil] = updatedAt.toLocalDateTime() + lockAtMostFor
            }
        } catch (e: IllegalStateException) {
            throw RuntimeException("Already locked")
        }
    }

    try {
        block.invoke()
    } finally {
        reactiveTransaction {
            Shedlocks.update({ Shedlocks.name eq name }) {
                it[lockUntil] = ZonedDateTime.now(UTC).toLocalDateTime()
            }
        }
    }
}

private fun insertNewShedlock(
    name: String,
    duration: Duration,
): ResultRow {
    Shedlocks.insert {
        it[Shedlocks.name] = name
        it[lockedAt] = nowLocalDateTime
        it[lockUntil] = nowLocalDateTime + duration
    }

    return Shedlocks.selectAll()
        .where { Shedlocks.name eq name }
        .pessimisticLock(NO_WAIT)
        .single()
}

private fun ResultRow.toShedLock() = Shedlock.of(this)

private val nowLocalDateTime
    get() = ZonedDateTime.now(UTC).toLocalDateTime()