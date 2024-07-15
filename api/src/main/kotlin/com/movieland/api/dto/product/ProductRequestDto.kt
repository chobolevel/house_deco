package com.movieland.api.dto.product

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.api.dto.product.image.CreateProductImageRequestWithProductDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestWithProductDto
import com.movieland.api.dto.product.option.CreateProductOptionRequestWithProductDto
import com.movieland.domain.entity.product.ProductStatusType
import com.movieland.domain.entity.product.ProductUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductRequestDto(
    @field:NotBlank(message = "상품 카테고리는 필수 값입니다.")
    val productCategoryId: String,
    @field:NotBlank(message = "상품 브랜드는 필수 값입니다.")
    val brandId: String,
    @field:NotBlank(message = "상품명은 필수 값입니다.")
    val name: String,
    @field:NotNull(message = "상품 메인 이미지는 필수 값입니다.")
    @field:NotEmpty(message = "상품 메인 이미지는 적어도 하나는 첨부되어야 합니다.")
    val images: List<CreateProductImageRequestWithProductDto>,
    @field:NotNull(message = "상품 필수 옵션은 필수 값입니다.")
    @field:NotEmpty(message = "상품 필수 옵션은 최소 1개 이상 등록해야합니다.")
    val requiredOptions: List<CreateProductOptionRequestWithProductDto>,
    val optionalOptions: List<CreateProductOptionRequestWithProductDto>?
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductRequestDto(
    val productCategoryId: String?,
    val brandId: String?,
    val name: String?,
    val status: ProductStatusType?,
    val images: List<UpdateProductImageRequestWithProductDto>?,
    @field:NotNull(message = "update_mask는 필수 값입니다.")
    @field:NotEmpty(message = "update_mask가 빈 배열입니다.")
    val updateMask: List<ProductUpdateMask>
)
