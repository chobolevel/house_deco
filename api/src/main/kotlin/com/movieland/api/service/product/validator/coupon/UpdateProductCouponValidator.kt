package com.movieland.api.service.product.validator.coupon

import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto
import com.movieland.domain.entity.product.coupon.ProductCouponUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class UpdateProductCouponValidator : UpdateProductCouponValidatable {

    override fun validate(request: UpdateProductCouponRequestDto) {
        request.updateMask.forEach {
            when (it) {
                ProductCouponUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 쿠폰명이 유효하지 않습니다."
                        )
                    }
                }

                ProductCouponUpdateMask.MIN_PURCHASE_AMOUNT -> {
                    if (request.minPurchaseAmount == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 쿠폰 적용 최소 금액이 유효하지 않습니다."
                        )
                    }
                }

                ProductCouponUpdateMask.DISCOUNT_PERCENT -> {
                    if (request.discountPercent == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 쿠폰 할인 퍼센트가 유효하지 않습니다."
                        )
                    }
                }

                ProductCouponUpdateMask.MAX_DISCOUNT_AMOUNT -> {
                    if (request.maxDiscountAmount == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 쿠폰 할인 최대 금액이 유효하지 않습니다."
                        )
                    }
                }

                ProductCouponUpdateMask.EXPIRED_AT -> {
                    if (request.expiredAt == null || request.expiredAt.isBefore(OffsetDateTime.now())) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 쿠폰 유효기간이 유효하지 않습니다."
                        )
                    }
                }
            }
        }
    }
}
