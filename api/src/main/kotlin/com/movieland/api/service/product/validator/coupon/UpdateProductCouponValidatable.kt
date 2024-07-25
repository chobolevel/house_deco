package com.movieland.api.service.product.validator.coupon

import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto

interface UpdateProductCouponValidatable {

    fun validate(request: UpdateProductCouponRequestDto)
}
