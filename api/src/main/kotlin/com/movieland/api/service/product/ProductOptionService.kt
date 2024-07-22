package com.movieland.api.service.product

import com.movieland.api.dto.product.option.CreateProductOptionRequestDto
import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto
import com.movieland.api.service.product.converter.ProductOptionConverter
import com.movieland.api.service.product.updater.ProductOptionUpdatable
import com.movieland.api.service.product.validator.ProductOptionUpdateValidatable
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.option.ProductOptionFinder
import com.movieland.domain.entity.product.option.ProductOptionRepository
import com.movieland.domain.entity.product.option.ProductOptionType
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.PolicyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionService(
    private val repository: ProductOptionRepository,
    private val finder: ProductOptionFinder,
    private val productFinder: ProductFinder,
    private val converter: ProductOptionConverter,
    private val updateValidators: List<ProductOptionUpdateValidatable>,
    private val updaters: List<ProductOptionUpdatable>
) {

    @Transactional
    fun createProductOption(request: CreateProductOptionRequestDto): Long {
        val productOption = converter.convert(request)
        return repository.save(productOption).id!!
    }

    @Transactional
    fun updateProductOption(id: Long, request: UpdateProductOptionRequestDto): Long {
        updateValidators.forEach { it.validate(request) }
        val productOption = finder.findById(id)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, productOption) }
        return productOption.id!!
    }

    @Transactional
    fun deleteProductOption(productId: Long, id: Long): Boolean {
        val product = productFinder.findById(productId)
        val productOption = finder.findById(id)
        if (productOption.type == ProductOptionType.REQUIRED && product.getRequiredOptions().size < 2) {
            throw PolicyException(
                errorCode = ErrorCode.PRODUCT_OPTION_IS_NOT_DELETABLE,
                message = "필수 옵션은 최소 1개이상 존재해야합니다."
            )
        }
        repository.delete(productOption)
        return true
    }
}
