package com.movieland.api.dto.product.review

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.review.ProductReviewUpdateMask
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductReviewRequestDto(
    @field:NotNull(message = "리뷰 평점은 필수 값입니다.")
    val rating: Double,
    @field:NotEmpty(message = "리뷰 내용은 필수 값입니다.")
    val comment: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductReviewRequestDto(
    val rating: Double?,
    val comment: String?,
    @field:NotNull(message = "update_mask는 필수 값입니다.")
    val updateMask: List<ProductReviewUpdateMask>
)
