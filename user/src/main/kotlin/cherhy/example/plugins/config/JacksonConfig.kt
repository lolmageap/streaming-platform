package cherhy.example.plugins.config

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureJackson() {
    install(ContentNegotiation) {
        jackson()
    }
}