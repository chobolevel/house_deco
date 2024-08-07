package com.movieland.api.dto.product.review

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.api.dto.product.ProductResponseDto
import com.movieland.api.dto.user.UserResponseDto

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProductReviewResponseDto(
    val id: Long,
    val reviewer: UserResponseDto,
    val productInfo: ProductResponseDto,
    val rating: Double,
    val comment: String,
    val createdAt: Long,
    val updatedAt: Long,
)
