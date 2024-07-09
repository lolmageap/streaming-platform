package com.cherhy.gateway.util.model

data class Principal private constructor(
    val userId: Long,
    val roles: List<String>,
) {
    companion object {
        fun of(
            userId: Long,
            roles: List<String>,
        ) = Principal(userId, roles)
    }
}