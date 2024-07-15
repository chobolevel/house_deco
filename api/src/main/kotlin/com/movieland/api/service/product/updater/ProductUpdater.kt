package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.api.service.product.converter.ProductImageConverter
import com.movieland.domain.entity.brand.BrandFinder
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductUpdateMask
import com.movieland.domain.entity.product.category.ProductCategoryFinder
import com.movieland.domain.entity.product.image.ProductImageType
import org.springframework.stereotype.Component

@Component
class ProductUpdater(
    private val productCategoryFinder: ProductCategoryFinder,
    private val brandFinder: BrandFinder,
    private val productImageConverter: ProductImageConverter
) : ProductUpdatable {

    override fun markAsUpdate(request: UpdateProductRequestDto, product: Product): Product {
        request.updateMask.forEach {
            when (it) {
                ProductUpdateMask.CATEGORY -> {
                    val productCategory = productCategoryFinder.findById(request.productCategoryId!!)
                    product.setBy(productCategory)
                }

                ProductUpdateMask.BRAND -> {
                    val brand = brandFinder.findById(request.brandId!!)
                    product.setBy(brand)
                }

                ProductUpdateMask.NAME -> product.name = request.name!!
                ProductUpdateMask.STATUS -> product.status = request.status!!
                ProductUpdateMask.IMAGES -> {
                    product.deleteAllImages()
                    request.images?.onEach { productImageConverter.convert(it, product) }
                }
                else -> Unit
            }
        }
        return product
    }

    override fun order(): Int {
        return 0
    }
}
