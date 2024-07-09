package com.cherhy.gateway.jwt

import com.cherhy.gateway.util.extension.accessToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationGlobalFilter(
    private val tokenDecoder: TokenDecoder,
) : WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain,
    ): Mono<Void> {
        val authentication = tokenDecoder.decode(exchange.accessToken)
        SecurityContextHolder.getContext().authentication = authentication
        return chain.filter(exchange)
    }
}