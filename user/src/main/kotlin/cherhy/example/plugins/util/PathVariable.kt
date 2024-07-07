package cherhy.example.plugins.util

import cherhy.example.plugins.domain.UserId
import com.cherhy.common.util.ID
import com.cherhy.common.util.USER_ID
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