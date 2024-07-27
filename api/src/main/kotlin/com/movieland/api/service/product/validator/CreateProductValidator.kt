package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.CreateProductRequestDto
import com.movieland.domain.entity.product.image.ProductImageType
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class CreateProductValidator : CreateProductValidatable {

    override fun validate(request: CreateProductRequestDto) {
        if (request.images.isEmpty()) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "상품 이미지는 반드시 1개 이상 첨부해야합니다."
            )
        }
        request.images.find { it.type == ProductImageType.MAIN } ?: throw ParameterInvalidException(
            errorCode = ErrorCode.INVALID_PARAMETER,
            message = "상품 이미지 중 하나는 반드시 MAIN 타입이어야 합니다."
        )
        if (request.detailImages.isEmpty()) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "상품 상세 이미지는 반드시 1개 이상 첨부해야합니다."
            )
        }
        if (request.requiredOptions.isEmpty()) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "상품 필수 옵션은 반드시 1개 이상 등록해야합니다."
            )
        }
    }
}
