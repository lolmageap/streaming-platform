package com.cherhy.plugins.config

import com.cherhy.common.config.UserIdResolver
import io.ktor.server.application.*

class UserIdResolverImpl(
    private val call: ApplicationCall,
): UserIdResolver {
    override fun resolve() =
        call.request.headers["user-id"]?.toLongOrNull()
            ?: throw IllegalArgumentException("user-id header is required")
}