package com.movieland.api.dto.product.option

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.option.ProductOptionType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductOptionRequestDto(
    val productId: Long,
    val type: ProductOptionType,
    val name: String,
    val originalPrice: Int,
    val salePrice: Int,
    val stock: Int,
    val order: Int,
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductOptionRequestWithProductDto(
    val type: ProductOptionType,
    val name: String,
    val originalPrice: Int,
    val salePrice: Int,
    val stock: Int,
    val order: Int,
)
