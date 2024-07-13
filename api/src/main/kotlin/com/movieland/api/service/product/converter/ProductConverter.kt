package com.movieland.api.service.product.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.product.CreateProductRequestDto
import com.movieland.api.dto.product.ProductResponseDto
import com.movieland.api.service.brand.converter.BrandConverter
import com.movieland.domain.entity.brand.BrandFinder
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductStatusType
import com.movieland.domain.entity.product.category.ProductCategoryFinder
import org.springframework.stereotype.Component

@Component
class ProductConverter(
    private val tsidFactory: TsidFactory,
    private val productCategoryFinder: ProductCategoryFinder,
    private val brandFinder: BrandFinder,
    private val productOptionConverter: ProductOptionConverter,
    private val productCategoryConverter: ProductCategoryConverter,
    private val brandConverter: BrandConverter
) {

    fun convert(request: CreateProductRequestDto): Product {
        val productCategory = productCategoryFinder.findById(request.productCategoryId)
        val brand = brandFinder.findById(request.brandId)
        return Product(
            id = tsidFactory.create().toString(),
            name = request.name,
            status = ProductStatusType.PREPARING,
        ).also { product ->
            product.setBy(productCategory)
            product.setBy(brand)
            request.requiredOptions.forEach { productOptionConverter.convertRequiredOptionWithProduct(it, product) }
            request.optionalOptions?.onEach { productOptionConverter.convertOptionOptionWithProduct(it, product) }
        }
    }

    fun convert(entity: Product): ProductResponseDto {
        return ProductResponseDto(
            id = entity.id,
            category = productCategoryConverter.convert(entity.productCategory!!),
            brand = brandConverter.convert(entity.brand!!),
            name = entity.name,
            status = entity.status,
            reviewCount = entity.reviewCount,
            reviewAverage = entity.reviewAverage,
            salesCount = entity.salesCount,
            requiredOptions = entity.getRequiredOptions().map { productOptionConverter.convert(it) },
            optionalOptions = entity.getOptionalOptions().map { productOptionConverter.convert(it) },
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }

}
