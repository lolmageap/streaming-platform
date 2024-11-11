package com.cherhy.common.util.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.roundOff(): BigDecimal =
    this.setScale(0, RoundingMode.HALF_EVEN)

fun BigDecimal.applyPercentage(
    percentage: BigDecimal,
    scale: Int = 0,
    roundingMode: RoundingMode = RoundingMode.UNNECESSARY,
): BigDecimal =
    this.divide(percentage, scale, roundingMode)