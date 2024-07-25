package com.movieland.api.dto.product.coupon

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProductCouponResponseDto(
    val id: Long,
    val name: String,
    val minPurchaseAmount: Int,
    val discountPercent: Int,
    val maxDiscountAmount: Int,
    val expiredAt: Long,
    val createdAt: Long,
    val updatedAt: Long
)
