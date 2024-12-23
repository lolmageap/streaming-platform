package com.cherhy.payment.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "r2dbc")
data class R2dbcDataSourceProperty(
    val master: R2dbcProperty,
    val slave: R2dbcProperty,
)

data class R2dbcProperty(
    val url: String,
    val username: String,
    val password: String,
)