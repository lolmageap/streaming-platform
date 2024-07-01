package com.cherhy.payment.router

import com.cherhy.common.util.Payment.PAYMENT_DOMAIN
import com.cherhy.common.util.Payment.PAYMENT_SERVICE
import com.cherhy.common.util.Stream.STREAM_DOMAIN
import com.cherhy.common.util.Stream.STREAM_SERVICE
import com.cherhy.common.util.User.USER_DOMAIN
import com.cherhy.common.util.User.USER_SERVICE
import com.cherhy.payment.jwt.TokenDecoder
import com.cherhy.payment.util.extension.accessToken
import com.cherhy.payment.util.extension.userId
import com.cherhy.payment.util.model.Principal
import com.cherhy.payment.util.property.DomainProperty
import com.nimbusds.jose.JOSEException
import mu.KotlinLogging
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Router(
    private val domainProperty: DomainProperty,
    private val tokenDecoder: TokenDecoder,
) {
    private val logger = KotlinLogging.logger {}

    @Bean
    fun routeLocator(
        builder: RouteLocatorBuilder,
    ) = builder.routes {
        route(PAYMENT_SERVICE) {
            path(PAYMENT_DOMAIN)
            uri(domainProperty.paymentUrl)
            filters {
                extractAndAddUserIdToHeader()
                setPath(PAYMENT_DOMAIN)
                rewritePath("/api/payment/(?<segment>.*)", "/payment/${'$'}{segment}")
            }
        }
        route(STREAM_SERVICE) {
            path(STREAM_DOMAIN)
            uri(domainProperty.streamUrl)
            filters {
                extractAndAddUserIdToHeader()
                setPath(STREAM_DOMAIN)
                rewritePath("/api/stream/(?<segment>.*)", "/stream/${'$'}{segment}")
            }
        }
        route(USER_SERVICE) {
            path(USER_DOMAIN)
            uri(domainProperty.userUrl)
            filters {
                extractAndAddUserIdToHeader()
                setPath(USER_DOMAIN)
                rewritePath("/api/user/(?<segment>.*)", "/user/${'$'}{segment}")
            }
        }
    }

    private fun GatewayFilterSpec.extractAndAddUserIdToHeader() {
        filter { exchange, chain ->
            val jwt = exchange.request.accessToken
            if (jwt != null) {
                try {
                    val principal = tokenDecoder.decode(jwt).principal as Principal
                    exchange.request.userId = principal.userId
                } catch (e: JOSEException) {
                    logger.info { "public 한 endpoint 에 만료된 jwt 요청이 들어왔습니다." }
                }
            }
            chain.filter(exchange)
        }
    }
}