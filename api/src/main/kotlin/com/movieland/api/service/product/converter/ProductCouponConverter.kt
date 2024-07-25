package com.movieland.api.service.product.converter

import com.movieland.api.dto.product.coupon.CreateProductCouponRequestDto
import com.movieland.api.dto.product.coupon.ProductCouponResponseDto
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.coupon.ProductCoupon
import org.springframework.stereotype.Component

@Component
class ProductCouponConverter {

    fun convert(request: CreateProductCouponRequestDto, product: Product): ProductCoupon {
        return ProductCoupon(
            name = request.name,
            minPurchaseAmount = request.minPurchaseAmount,
            discountPercent = request.discountPercent,
            maxDiscountAmount = request.maxDiscountAmount,
            expiredAt = request.expiredAt,
        ).also {
            it.setBy(product)
        }
    }

    fun convert(entity: ProductCoupon): ProductCouponResponseDto {
        return ProductCouponResponseDto(
            id = entity.id!!,
            name = entity.name,
            minPurchaseAmount = entity.minPurchaseAmount,
            discountPercent = entity.discountPercent,
            maxDiscountAmount = entity.maxDiscountAmount,
            expiredAt = entity.expiredAt.toInstant().toEpochMilli(),
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli(),
        )
    }
}
