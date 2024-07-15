package com.movieland.api.service.product.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.product.image.CreateProductImageRequestDto
import com.movieland.api.dto.product.image.CreateProductImageRequestWithProductDto
import com.movieland.api.dto.product.image.ProductImageResponseDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestWithProductDto
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.image.ProductImage
import com.movieland.domain.entity.product.image.ProductImageType
import org.springframework.stereotype.Component

@Component
class ProductImageConverter(
    private val tsidFactory: TsidFactory,
) {

    fun convert(request: CreateProductImageRequestDto, product: Product): ProductImage {
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(request: CreateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(request: UpdateProductImageRequestWithProductDto, product: Product): ProductImage {
        return ProductImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = request.type
        ).also {
            it.setBy(product)
        }
    }

    fun convert(entity: ProductImage): ProductImageResponseDto {
        return ProductImageResponseDto(
            id = entity.id,
            originUrl = entity.originUrl,
            name = entity.name,
            type = entity.type,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
