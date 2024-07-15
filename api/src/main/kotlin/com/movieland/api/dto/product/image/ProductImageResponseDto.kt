package com.movieland.api.dto.product.image

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.image.ProductImageType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProductImageResponseDto(
    val id: String,
    val originUrl: String,
    val name: String,
    val type: ProductImageType,
    val createdAt: Long,
    val updatedAt: Long
)
