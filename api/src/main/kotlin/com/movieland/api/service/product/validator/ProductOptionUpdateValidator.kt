package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto
import com.movieland.domain.entity.product.option.ProductOptionUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class ProductOptionUpdateValidator : ProductOptionUpdateValidatable {
    override fun validate(request: UpdateProductOptionRequestDto) {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_OPTION_NAME,
                            message = "유효하지 않은 상품 옵션명입니다."
                        )
                    }
                }

                ProductOptionUpdateMask.ORIGINAL_PRICE -> {
                    if (request.originalPrice == null || request.originalPrice < 100) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_OPTION_ORIGINAL_PRICE,
                            message = "상품 옵션 원가가 유효하지 않거나 최소 100원 이상이어야 합니다."
                        )
                    }
                }

                ProductOptionUpdateMask.SALE_PRICE -> {
                    if (request.salePrice == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_OPTION_SALE_PRICE,
                            message = "상품 옵션 판매가가 유효하지 않습니다. "
                        )
                    }
                }

                ProductOptionUpdateMask.STOCK -> {
                    if (request.stock == null || request.stock < 1) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_OPTION_STOCK,
                            message = "상품 옵션 재고 수량이 유효하지 않거나 최소 1개 이상이어야 합니다."
                        )
                    }
                }

                ProductOptionUpdateMask.ORDER -> {
                    if (request.order == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_OPTION_ORDER,
                            message = "상품 옵션 노출 순서가 유효하지 않습니다."
                        )
                    }
                }
            }
        }
    }
}
