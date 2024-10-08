package com.movieland.domain.entity.product.option

import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class ProductOptionFinder(
    private val repository: ProductOptionRepository
) {

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): ProductOption {
        return repository.findByIdAndDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_OPTION_IS_NOT_FOUND,
            message = "상품 옵션 정보를 찾을 수 없습니다."
        )
    }
}
