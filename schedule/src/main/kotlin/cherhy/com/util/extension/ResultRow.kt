package cherhy.com.util.extension

import extension.ktor.exposed.Shedlock
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toShedlock() = Shedlock.of(this)