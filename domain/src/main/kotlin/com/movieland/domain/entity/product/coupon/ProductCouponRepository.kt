package com.movieland.domain.entity.product.coupon

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCouponRepository : JpaRepository<ProductCoupon, Long> {

    fun findByIdAndProductIdAndDeletedFalse(id: Long, productId: Long): ProductCoupon?
}
