package com.cherhy.event

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseVideoEvent(
    val userId: Long,
    val videoId: Long,
    val price: Long,
)

@Serializable
data class VideoPurchaseFailedEvent(
    val userId: Long,
    val videoId: Long,
    val price: Long,
)