package cherhy.com.plugins

import cherhy.com.util.constant.TimeToDuration.TEN_MINUTE
import extension.ktor.schedule
import extension.ktor.shedlock
import io.ktor.server.application.*

fun Application.configureScheduler() {
    schedule(FIVE_PM) {
        shedlock(TEST_SCHEDULED, TEN_MINUTE) {
            println("hi")
        }
    }
}

private const val TEST_SCHEDULED = "test"
private const val FIVE_PM = "30 41 23 * * ?"