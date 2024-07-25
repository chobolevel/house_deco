package com.movieland.domain.entity.product.coupon

import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class ProductCouponFinder(
    private val repository: ProductCouponRepository
) {

    fun findByIdAndProductId(id: Long, productId: Long): ProductCoupon {
        return repository.findByIdAndProductIdAndDeletedFalse(id, productId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_COUPON_IS_NOT_FOUND,
            message = "쿠폰 정보를 찾을 수 없습니다."
        )
    }
}
