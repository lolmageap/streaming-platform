package cherhy.com.plugins

import cherhy.com.util.constant.TimeToDuration.TEN_MINUTE
import cherhy.com.util.constant.TimeToLong.ONE_DAY
import cherhy.com.util.constant.TimeToLong.ONE_SECOND
import cherhy.com.util.schedule
import cherhy.com.util.shedlock
import com.cherhy.common.util.extension.toSeoulTime
import io.ktor.server.application.*
import kotlinx.coroutines.delay
import java.time.ZonedDateTime

fun Application.configureScheduler() {
    schedule {
        if (now.isFivePM) {
            shedlock(TEST_SCHEDULED, TEN_MINUTE) {
                delay(ONE_DAY)
            }
        } else {
            delay(ONE_SECOND)
        }
    }
}

private const val TEST_SCHEDULED = "test"

private val now
    get() = ZonedDateTime.now().toSeoulTime()

private val ZonedDateTime.isFivePM
    get() = this.hour == 17 && this.minute == 0 && this.second == 0