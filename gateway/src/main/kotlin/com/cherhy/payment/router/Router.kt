package com.cherhy.payment.router

import com.cherhy.common.util.Payment.PAYMENT_DOMAIN
import com.cherhy.common.util.Payment.PAYMENT_SERVICE
import com.cherhy.common.util.Stream.STREAM_DOMAIN
import com.cherhy.common.util.Stream.STREAM_SERVICE
import com.cherhy.common.util.User.USER_DOMAIN
import com.cherhy.common.util.User.USER_SERVICE
import com.cherhy.payment.util.property.DomainProperty
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Router(
    private val domainProperty: DomainProperty,
) {
    // TODO: jwt에서 userId를 추출해서 라우터에 보내는 로직을 추가해야함. -> SecurityConfig에서 처리
    @Bean
    fun routeLocator(
        builder: RouteLocatorBuilder,
    ) = builder.routes {
        route(PAYMENT_SERVICE) {
            path(PAYMENT_DOMAIN)
            uri(domainProperty.paymentUrl)
        }
        route(STREAM_SERVICE) {
            path(STREAM_DOMAIN)
            uri(domainProperty.streamUrl)
        }
        route(USER_SERVICE) {
            path(USER_DOMAIN)
            uri(domainProperty.userUrl)
        }
    }
}