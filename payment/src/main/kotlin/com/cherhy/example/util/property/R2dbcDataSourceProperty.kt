package com.cherhy.example.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "r2dbc")
data class R2dbcDataSourceProperty(
    val slave: R2dbcProperty,
    val master: R2dbcProperty,
)

data class R2dbcProperty(
    val url: String,
    val username: String,
    val password: String,
)