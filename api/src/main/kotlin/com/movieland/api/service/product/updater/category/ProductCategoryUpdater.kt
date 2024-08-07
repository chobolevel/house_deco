package com.movieland.api.service.product.updater.category

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.domain.entity.product.category.ProductCategory
import com.movieland.domain.entity.product.category.ProductCategoryFinder
import com.movieland.domain.entity.product.category.ProductCategoryUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductCategoryUpdater(
    private val finder: ProductCategoryFinder
) : ProductCategoryUpdatable {

    override fun markAsUpdate(
        request: UpdateProductCategoryRequestDto,
        productCategory: ProductCategory
    ): ProductCategory {
        request.updateMask.forEach {
            when (it) {
                ProductCategoryUpdateMask.PARENT -> {
                    if (request.parentId == null) {
                        productCategory.resetParent()
                    } else {
                        val parent = finder.findById(request.parentId)
                        productCategory.setBy(parent)
                    }
                }

                ProductCategoryUpdateMask.NAME -> productCategory.name = request.name!!
            }
        }
        return productCategory
    }

    override fun order(): Int {
        return 0
    }
}
