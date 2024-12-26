package com.cherhy.event

data class BuyVideoEvent(
    val userId: Long,
    val videoId: Long,
    val price: Long,
)