package com.movieland.domain.entity.product.image

import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductImageFinder(
    private val repository: ProductImageRepository
) {

    fun findById(id: String): ProductImage {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_IMAGE_IS_NOT_FOUND,
            message = "상품 이미지를 찾을 수 없습니다."
        )
    }

}
