package com.movieland.api.service.product.validator.coupon

import com.movieland.api.dto.product.coupon.CreateProductCouponRequestDto

interface CreateProductCouponValidatable {

    fun validate(request: CreateProductCouponRequestDto)
}
