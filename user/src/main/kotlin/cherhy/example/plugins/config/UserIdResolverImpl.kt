package cherhy.example.plugins.config

import cherhy.example.plugins.util.constant.USER_ID
import com.cherhy.common.config.UserIdResolver
import io.ktor.server.application.*

class UserIdResolverImpl(
    private val call: ApplicationCall,
): UserIdResolver {
    override fun resolve() =
        call.request.headers[USER_ID]?.toLongOrNull()
            ?: throw IllegalArgumentException("user-id header is required")
}