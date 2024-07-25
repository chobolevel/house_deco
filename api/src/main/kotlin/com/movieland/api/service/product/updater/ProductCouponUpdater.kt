package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto
import com.movieland.domain.entity.product.coupon.ProductCoupon
import com.movieland.domain.entity.product.coupon.ProductCouponUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductCouponUpdater : ProductCouponUpdatable {

    override fun markAsUpdate(request: UpdateProductCouponRequestDto, productCoupon: ProductCoupon): ProductCoupon {
        request.updateMask.forEach {
            when (it) {
                ProductCouponUpdateMask.NAME -> productCoupon.name = request.name!!
                ProductCouponUpdateMask.MIN_PURCHASE_AMOUNT ->
                    productCoupon.minPurchaseAmount =
                        request.minPurchaseAmount!!

                ProductCouponUpdateMask.DISCOUNT_PERCENT -> productCoupon.discountPercent = request.discountPercent!!
                ProductCouponUpdateMask.MAX_DISCOUNT_AMOUNT ->
                    productCoupon.maxDiscountAmount =
                        request.maxDiscountAmount!!

                ProductCouponUpdateMask.EXPIRED_AT -> productCoupon.expiredAt = request.expiredAt!!
            }
        }
        return productCoupon
    }

    override fun order(): Int {
        return 0
    }
}
