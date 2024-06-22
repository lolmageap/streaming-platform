package com.cherhy.payment.router

import com.cherhy.payment.util.constant.Domain.PAYMENT_SERVICE
import com.cherhy.payment.util.constant.Domain.PAYMENT_SERVICE_PATH
import com.cherhy.payment.util.constant.Domain.STREAM_SERVICE
import com.cherhy.payment.util.constant.Domain.STREAM_SERVICE_PATH
import com.cherhy.payment.util.constant.Domain.USER_SERVICE
import com.cherhy.payment.util.constant.Domain.USER_SERVICE_PATH
import com.cherhy.payment.util.property.DomainProperty
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Router(
    private val domainProperty: DomainProperty,
) {
    @Bean
    fun routeLocator(
        builder: RouteLocatorBuilder,
    ) = builder.routes {
        route(PAYMENT_SERVICE) {
            path(PAYMENT_SERVICE_PATH)
            uri(domainProperty.paymentUrl)
        }
        route(STREAM_SERVICE) {
            path(STREAM_SERVICE_PATH)
            uri(domainProperty.streamUrl)
        }
        route(USER_SERVICE) {
            path(USER_SERVICE_PATH)
            uri(domainProperty.userUrl)
        }
    }
}