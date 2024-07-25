package com.movieland.api.dto.product.coupon

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.coupon.ProductCouponUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductCouponRequestDto(
    @field:NotBlank(message = "쿠폰명이 입력되지 않았습니다.")
    val name: String,
    @field:NotNull(message = "쿠폰 적용 최소 가격이 입력되지 않았습니다.")
    val minPurchaseAmount: Int,
    @field:NotNull(message = "쿠폰 할인 퍼센트가 입력되지 않았습니다.")
    val discountPercent: Int,
    @field:NotNull(message = "쿠폰 최대 할인 금액이 입력되지 않았습니다.")
    val maxDiscountAmount: Int,
    @field:NotNull(message = "쿠폰 만료 일자가 입력되지 않았습니다.")
    val expiredAt: OffsetDateTime
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductCouponRequestDto(
    val name: String?,
    val minPurchaseAmount: Int?,
    val discountPercent: Int?,
    val maxDiscountAmount: Int?,
    val expiredAt: OffsetDateTime?,
    @field:NotNull(message = "update_mask는 필수 값입니다.")
    val updateMask: List<ProductCouponUpdateMask>
)
