package com.cherhy.payment.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("domain")
data class DomainProperty(
    val paymentUrl: String,
    val streamUrl: String,
    val userUrl: String,
)