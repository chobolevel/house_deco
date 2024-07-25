package com.movieland.api.service.product.validator.category

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.domain.entity.product.category.ProductCategoryUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class ProductCategoryUpdateValidator : ProductCategoryUpdateValidatable {

    override fun validate(request: UpdateProductCategoryRequestDto) {
        request.updateMask.forEach {
            when (it) {
                ProductCategoryUpdateMask.PARENT -> {
                    if (request.parentId == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_CATEGORY_PARENT,
                            message = "유효하지 않은 부모 카테고리입니다."
                        )
                    }
                }

                ProductCategoryUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PRODUCT_CATEGORY_NAME,
                            message = "유효하지 않은 카테고리 이름입니다."
                        )
                    }
                }
            }
        }
    }
}
