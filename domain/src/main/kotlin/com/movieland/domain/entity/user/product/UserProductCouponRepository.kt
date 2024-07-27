package com.movieland.domain.entity.user.product

import org.springframework.data.jpa.repository.JpaRepository

interface UserProductCouponRepository : JpaRepository<UserProductCoupon, Long> {

    fun existsByUserIdAndProductCouponId(userId: Long, productCouponId: Long): Boolean
}
