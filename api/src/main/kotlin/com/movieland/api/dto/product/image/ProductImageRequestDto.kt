package com.movieland.api.dto.product.image

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.image.ProductImageType
import com.movieland.domain.entity.product.image.ProductImageUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductImageRequestDto(
    @field:NotBlank(message = "상품 정보는 필수 값입니다.")
    val productId: String,
    @field:NotBlank(message = "이미지 URL은 필수 값입니다.")
    val originUrl: String,
    @field:NotBlank(message = "이미지 파일명은 필수 값입니다.")
    val name: String,
    @field:NotNull(message = "이미지 타입은 필수 값입니다.")
    var type: ProductImageType
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductImageRequestWithProductDto(
    @field:NotBlank(message = "이미지 URL은 필수 값입니다.")
    val originUrl: String,
    @field:NotBlank(message = "이미지 파일명은 필수 값입니다.")
    val name: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductImageRequestDto(
    val originUrl: String?,
    val name: String?,
    val type: ProductImageType?,
    @field:NotNull(message = "update_mask는 필수 값입니다.")
    @field:NotEmpty(message = "update_mask가 빈 배열입니다.")
    val updateMask: List<ProductImageUpdateMask>
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductImageRequestWithProductDto(
    @field:NotBlank(message = "이미지 URL은 필수 값입니다.")
    val originUrl: String,
    @field:NotBlank(message = "이미지 파일명은 필수 값입니다.")
    val name: String
)
