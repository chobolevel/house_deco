package com.movieland.api.service.product.converter

import com.movieland.api.dto.product.image.CreateProductImageRequestDto
import com.movieland.api.dto.product.image.CreateProductImageRequestWithProductDto
import com.movieland.api.dto.product.image.ProductImageResponseDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestWithProductDto
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.image.ProductImage
import org.springframework.stereotype.Component

@Component
class ProductImageConverter() {

    fun convert(request: CreateProductImageRequestDto, product: Product): ProductImage {
        return ProductImage(
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(request: CreateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(request: UpdateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(entity: ProductImage): ProductImageResponseDto {
        return ProductImageResponseDto(
            id = entity.id!!,
            originUrl = entity.originUrl,
            name = entity.name,
            type = entity.type,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
