package com.movieland.api.service.product.validator.review

import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto
import com.movieland.domain.entity.product.review.ProductReviewUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class UpdateProductReviewValidator : UpdateProductReviewValidatable {

    override fun validate(request: UpdateProductReviewRequestDto) {
        request.updateMask.forEach {
            when (it) {
                ProductReviewUpdateMask.RATING -> {
                    if (request.rating == null || request.rating.isNaN()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "상품 후기 평점이 유효하지 않습니다."
                        )
                    }
                }

                ProductReviewUpdateMask.COMMENT -> {
                    if (request.comment.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "상품 후기 내용이 유효하지 않습니다."
                        )
                    }
                }
            }
        }
    }
}
