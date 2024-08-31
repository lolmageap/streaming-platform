package cherhy.com.plugins

import cherhy.com.util.constant.TimeToDuration.TEN_MINUTE
import cherhy.com.util.schedule
import cherhy.com.util.shedlock
import io.ktor.server.application.*

fun Application.configureScheduler() {
    schedule(FIVE_PM) {
        shedlock(TEST_SCHEDULED, TEN_MINUTE) {
            TODO("execute business logic")
        }
    }
}

private const val TEST_SCHEDULED = "test"
private const val FIVE_PM = "0 0 17 * * *"