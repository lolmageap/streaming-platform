package com.cherhy.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.cherhy.payment.util.property")
class GatewayApplication

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}