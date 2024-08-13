package com.movieland.domain.entity.product.coupon

import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime

interface ProductCouponRepository : JpaRepository<ProductCoupon, Long> {

    fun findByIdAndExpiredAtAfter(id: Long, expiredAt: OffsetDateTime): ProductCoupon?

    fun findByIdAndProductIdAndDeletedFalse(id: Long, productId: Long): ProductCoupon?
}
