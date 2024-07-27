package com.movieland.api.dto.user.product

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.api.dto.product.coupon.ProductCouponResponseDto

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserProductCouponResponseDto(
    val id: Long,
    val couponName: String,
    val couponMinPurchaseAMount: Int,
    val couponDiscountPercent: Int,
    val couponMaxDiscountAmount: Int,
    val couponExpiredAt: Long,
    val used: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
)
