package com.movieland.api.dto.product.category

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.category.ProductCategoryUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductCategoryRequestDto(
    val parentId: String?,
    @field:NotBlank(message = "상품 카테고리 이름은 필수 값입니다.")
    val name: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductCategoryRequestDto(
    val parentId: String?,
    val name: String?,
    @field:NotEmpty(message = "update_mask는 필수 값입니다.")
    val updateMask: List<ProductCategoryUpdateMask>
)
