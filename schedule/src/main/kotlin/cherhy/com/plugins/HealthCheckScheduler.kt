package cherhy.com.plugins

import cherhy.com.util.call
import cherhy.com.util.httpClient
import extension.ktor.exposed.shedlock
import extension.ktor.schedule
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.server.application.*
import mu.KotlinLogging
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

fun Application.healthCheckScheduler() {
    val logger = KotlinLogging.logger { }

    schedule(1.minutes) {
        shedlock(HEALTH_CHECK_SCHEDULED, 59.seconds) {
            val value = httpClient.call<String>(HEALTH_CHECK_URL, Get)
            val hasValue = value.isNotEmpty()

            logger.info { "Health check success=$hasValue" }
        }
    }
}

const val HEALTH_CHECK_SCHEDULED = "healthCheck"
const val HEALTH_CHECK_URL = "https://www.google.com"