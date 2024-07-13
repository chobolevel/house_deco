package com.movieland.api.service.product.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.product.category.CreateProductCategoryRequestDto
import com.movieland.api.dto.product.category.ProductCategoryResponseDto
import com.movieland.domain.entity.product.category.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductCategoryConverter(
    private val tsidFactory: TsidFactory
) {

    fun convert(request: CreateProductCategoryRequestDto): ProductCategory {
        return ProductCategory(
            id = tsidFactory.create().toString(),
            name = request.name
        )
    }

    fun convert(entity: ProductCategory): ProductCategoryResponseDto {
        return ProductCategoryResponseDto(
            id = entity.id,
            name = entity.name,
            categories = entity.categories.map { convert(it) }
        )
    }
}
