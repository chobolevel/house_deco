package com.movieland.api.service.product

import com.movieland.api.dto.product.image.CreateProductImageRequestDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.api.service.product.converter.ProductImageConverter
import com.movieland.api.service.product.updater.ProductImageUpdatable
import com.movieland.domain.entity.product.image.ProductImageFinder
import com.movieland.domain.entity.product.image.ProductImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductImageService(
    private val repository: ProductImageRepository,
    private val finder: ProductImageFinder,
    private val converter: ProductImageConverter,
    private val updaters: List<ProductImageUpdatable>
) {

    @Transactional
    fun createProductImage(request: CreateProductImageRequestDto): String {
        val productImage = converter.convert(request)
        return repository.save(productImage).id
    }

    @Transactional
    fun updateProductImage(id: String, request: UpdateProductImageRequestDto): String {
        val productImage = finder.findById(id)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, productImage) }
        return productImage.id
    }

    @Transactional
    fun deleteProductImage(id: String): Boolean {
        val productImage = finder.findById(id)
        repository.delete(productImage)
        return true
    }
}
