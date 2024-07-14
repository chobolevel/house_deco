package com.movieland.api.service.product.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.product.image.CreateProductImageRequestDto
import com.movieland.api.dto.product.image.CreateProductImageRequestWithProductDto
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.image.ProductImage
import com.movieland.domain.entity.product.image.ProductImageType
import org.springframework.stereotype.Component

@Component
class ProductImageConverter(
    private val tsidFactory: TsidFactory,
    private val productFinder: ProductFinder
) {

    fun convert(request: CreateProductImageRequestDto): ProductImage {
        val product = productFinder.findById(request.productId)
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convertToMainImage(request: CreateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = ProductImageType.MAIN,
        ).also {
            it.setBy(product)
        }
    }

    fun convertToDescriptionImage(request: CreateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = ProductImageType.DESCRIPTION,
        ).also {
            it.setBy(product)
        }
    }
}
