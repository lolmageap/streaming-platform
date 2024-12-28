package com.cherhy.common.util.event

import java.math.BigDecimal

data class PurchaseVideoEvent(
    val userId: Long,
    val videoId: Long,
    val price: BigDecimal,
)

data class VideoPurchaseFailedEvent(
    val userId: Long,
    val videoId: Long,
    val price: BigDecimal,
)