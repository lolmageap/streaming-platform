package com.cherhy.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.cherhy.producer",
        "com.cherhy.consumer",
        "com.cherhy.payment",
    ],
    exclude = [
        R2dbcAutoConfiguration::class,
        DataSourceAutoConfiguration::class,
    ],
)
@ConfigurationPropertiesScan("com.cherhy.payment.util.property")
class PaymentApplication

fun main(args: Array<String>) {
    runApplication<PaymentApplication>(*args)
}
