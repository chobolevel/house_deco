package com.movieland.api.service.product.validator.coupon

import com.movieland.api.dto.product.coupon.CreateProductCouponRequestDto
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class CreateProductCouponValidator : CreateProductCouponValidatable {

    override fun validate(request: CreateProductCouponRequestDto) {
        if (request.name.isEmpty()) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "쿠폰명이 유효하지 않습니다."
            )
        }
        if (request.expiredAt.isBefore(OffsetDateTime.now())) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "쿠폰 유효기간은 반드시 오늘보다 이후여야 합니다."
            )
        }
    }
}
