package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto
import com.movieland.domain.entity.product.coupon.ProductCoupon

interface ProductCouponUpdatable {

    fun markAsUpdate(request: UpdateProductCouponRequestDto, productCoupon: ProductCoupon): ProductCoupon

    fun order(): Int
}
