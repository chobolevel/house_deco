package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.domain.entity.brand.BrandFinder
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.ProductUpdateMask
import com.movieland.domain.entity.product.category.ProductCategoryFinder
import org.springframework.stereotype.Component

@Component
class ProductUpdater(
    private val productCategoryFinder: ProductCategoryFinder,
    private val brandFinder: BrandFinder,
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
                else -> Unit
            }
        }
        return product
    }

    override fun order(): Int {
        return 0
    }
}
