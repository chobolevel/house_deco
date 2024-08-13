package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.domain.entity.product.ProductUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class ProductUpdateValidator : ProductUpdateValidatable {

    override fun validate(request: UpdateProductRequestDto) {
        request.updateMask.forEach {
            when (it) {
                ProductUpdateMask.CATEGORY -> {
                    if (request.productCategoryId == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 상품 카테고리 ID가 누락되었습니다."
                        )
                    }
                }

                ProductUpdateMask.BRAND -> {
                    if (request.brandId == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 브랜드 ID가 누락되었습니다."
                        )
                    }
                }

                ProductUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 상품 이름이 누락되었습니다."
                        )
                    }
                }

                ProductUpdateMask.STATUS -> {
                    if (request.status == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 상품 상태 정보가 누락되었습니다."
                        )
                    }
                }

                ProductUpdateMask.IMAGES -> {
                    if (request.images.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 이미지가 없습니다."
                        )
                    }
                }

                ProductUpdateMask.DETAIL_IMAGES -> {
                    if (request.detailImages.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 상세 이미지가 없습니다."
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}
