package cherhy.example.plugins.util

import cherhy.example.plugins.domain.user.UserId
import cherhy.example.plugins.util.Path.ID
import cherhy.example.plugins.util.Path.USER_ID
import io.ktor.server.application.*

class PathVariable(
    call: ApplicationCall,
) {
    operator fun get(
        key: String,
    ) =
        path[key]
            ?: throw IllegalArgumentException("$key is required.")

    val id = call.parameters[ID]
        ?.toLongOrNull()
        ?: throw IllegalArgumentException("$ID is required.")

    val userId = call.parameters[USER_ID]
        ?.toLong()?.let(UserId::of)
        ?: throw IllegalArgumentException("$USER_ID is required.")

    private val path = call.parameters
}

object Path {
    const val ID = "id"
    const val USER_ID = "user-id"
}