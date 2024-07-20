package com.movieland.api.dto.product.option

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.product.option.ProductOptionType
import com.movieland.domain.entity.product.option.ProductOptionUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductOptionRequestDto(
    @field:NotNull(message = "상품 아이디는 필수 값입니다.")
    val productId: Long,
    @field:NotNull(message = "상품 옵션 타입은 필수 값입니다.")
    val type: ProductOptionType,
    @field:NotBlank(message = "상품 옵션명은 필수 값입니다.")
    val name: String,
    @field:NotNull(message = "상품 옵션 원가는 필수 값입니다.")
    val originalPrice: Int,
    @field:NotNull(message = "상품 옵션 판매가는 필수 값입니다.")
    val salePrice: Int,
    @field:NotNull(message = "상품 옵션 재고는 필수 값입니다.")
    val stock: Int,
    @field:NotNull(message = "상품 옵션 노출 순서는 필수 값입니다.")
    val order: Int,
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateProductOptionRequestWithProductDto(
    @field:NotNull(message = "상품 옵션 타입은 필수 값입니다.")
    val type: ProductOptionType,
    @field:NotBlank(message = "상품 옵션명은 필수 값입니다.")
    val name: String,
    @field:NotNull(message = "상품 옵션 원가는 필수 값입니다.")
    val originalPrice: Int,
    @field:NotNull(message = "상품 옵션 판매가는 필수 값입니다.")
    val salePrice: Int,
    @field:NotNull(message = "상품 옵션 재고는 필수 값입니다.")
    val stock: Int,
    @field:NotNull(message = "상품 옵션 노출 순서는 필수 값입니다.")
    val order: Int,
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateProductOptionRequestDto(
    val name: String?,
    val originalPrice: Int?,
    val salePrice: Int?,
    val stock: Int?,
    val order: Int?,
    @field:NotNull(message = "update_mask는 필수 값입니다.")
    @field:NotEmpty(message = "update_mask가 빈 배열입니다.")
    val updateMask: List<ProductOptionUpdateMask>
)
