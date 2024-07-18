package com.movieland.api.dto.product.category

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProductCategoryResponseDto(
    val id: Long,
    val name: String,
    val categories: List<ProductCategoryResponseDto>
)
