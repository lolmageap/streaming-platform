package cherhy.example.util

import io.ktor.server.application.*

class PathVariable(
    call: ApplicationCall,
) {
    operator fun get(
        key: String,
    ) =
        path[key]
            ?: throw IllegalArgumentException("$key is required.")

    private val path = call.parameters
}