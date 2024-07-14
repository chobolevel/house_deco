package com.movieland.api.service.product.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.product.option.CreateProductOptionRequestDto
import com.movieland.api.dto.product.option.CreateProductOptionRequestWithProductDto
import com.movieland.api.dto.product.option.ProductOptionResponseDto
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.option.ProductOption
import com.movieland.domain.entity.product.option.ProductOptionStatus
import com.movieland.domain.entity.product.option.ProductOptionType
import org.springframework.stereotype.Component

@Component
class ProductOptionConverter(
    private val tsidFactory: TsidFactory,
    private val productFinder: ProductFinder
) {

    fun convert(request: CreateProductOptionRequestDto): ProductOption {
        val product = productFinder.findById(request.productId)
        return ProductOption(
            id = tsidFactory.create().toString(),
            type = request.type,
            status = ProductOptionStatus.PREPARING,
            name = request.name,
            originalPrice = request.originalPrice,
            salePrice = request.originalPrice,
            stock = request.stock,
            order = request.order
        ).also {
            it.setBy(product)
        }
    }

    fun convertRequiredOptionWithProduct(
        request: CreateProductOptionRequestWithProductDto,
        product: Product
    ): ProductOption {
        return ProductOption(
            id = tsidFactory.create().toString(),
            type = ProductOptionType.REQUIRED,
            status = ProductOptionStatus.PREPARING,
            name = request.name,
            originalPrice = request.originalPrice,
            salePrice = request.salePrice,
            stock = request.stock,
            order = request.order
        ).also {
            it.setBy(product)
        }
    }

    fun convertOptionOptionWithProduct(
        request: CreateProductOptionRequestWithProductDto,
        product: Product
    ): ProductOption {
        return ProductOption(
            id = tsidFactory.create().toString(),
            type = ProductOptionType.OPTIONAL,
            status = ProductOptionStatus.PREPARING,
            name = request.name,
            originalPrice = request.originalPrice,
            salePrice = request.salePrice,
            stock = request.stock,
            order = request.order
        ).also {
            it.setBy(product)
        }
    }

    fun convert(entity: ProductOption): ProductOptionResponseDto {
        return ProductOptionResponseDto(
            id = entity.id,
            type = entity.type,
            status = entity.status,
            name = entity.name,
            originalPrice = entity.originalPrice,
            salePrice = entity.salePrice,
            stock = entity.stock,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
