package com.movieland.api.dto.product

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.api.dto.brand.BrandResponseDto
import com.movieland.api.dto.product.category.ProductCategorySimpleResponseDto
import com.movieland.api.dto.product.coupon.ProductCouponResponseDto
import com.movieland.api.dto.product.image.ProductImageResponseDto
import com.movieland.api.dto.product.option.ProductOptionResponseDto
import com.movieland.domain.entity.product.ProductStatusType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProductResponseDto(
    val id: Long,
    val category: ProductCategorySimpleResponseDto,
    val brand: BrandResponseDto,
    val name: String,
    val status: ProductStatusType,
    val reviewCount: Int,
    val reviewAverage: Double,
    val salesCount: Int,
    val images: List<ProductImageResponseDto>,
    val detailImages: List<ProductImageResponseDto>,
    val requiredOptions: List<ProductOptionResponseDto>,
    val optionalOptions: List<ProductOptionResponseDto>,
    val coupons: List<ProductCouponResponseDto>,
    val createdAt: Long,
    val updatedAt: Long
)
