package com.movieland.api.service.user.converter

import com.movieland.api.dto.user.product.UserProductCouponResponseDto
import com.movieland.domain.entity.product.coupon.ProductCoupon
import com.movieland.domain.entity.user.User
import com.movieland.domain.entity.user.product.UserProductCoupon
import org.springframework.stereotype.Component

@Component
class UserProductCouponConverter {

    fun convert(user: User, productCoupon: ProductCoupon): UserProductCoupon {
        return UserProductCoupon()
            .also {
                it.setBy(user)
                it.setBy(productCoupon)
            }
    }

    fun convert(entity: UserProductCoupon): UserProductCouponResponseDto {
        val productCoupon = entity.productCoupon!!
        return UserProductCouponResponseDto(
            id = entity.id!!,
            couponName = productCoupon.name,
            couponMinPurchaseAMount = productCoupon.minPurchaseAmount,
            couponDiscountPercent = productCoupon.discountPercent,
            couponMaxDiscountAmount = productCoupon.maxDiscountAmount,
            couponExpiredAt = productCoupon.expiredAt.toInstant().toEpochMilli(),
            used = entity.used,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli(),
        )
    }
}
