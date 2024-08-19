package cherhy.com.plugins

import cherhy.com.router.home
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        home()
    }
}
