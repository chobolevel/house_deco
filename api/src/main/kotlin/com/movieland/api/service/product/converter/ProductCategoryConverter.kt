package com.movieland.api.service.product.converter

import com.movieland.api.dto.product.category.CreateProductCategoryRequestDto
import com.movieland.api.dto.product.category.ProductCategoryResponseDto
import com.movieland.domain.entity.product.category.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductCategoryConverter() {

    fun convert(request: CreateProductCategoryRequestDto): ProductCategory {
        return ProductCategory(
            name = request.name
        )
    }

    fun convert(entity: ProductCategory): ProductCategoryResponseDto {
        return ProductCategoryResponseDto(
            id = entity.id!!,
            name = entity.name,
            categories = entity.categories.map { convert(it) }
        )
    }
}
