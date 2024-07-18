package com.movieland.api.service.product.query

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.ProductQueryFilter
import org.springframework.stereotype.Component

@Component
class ProductQueryCreator {

    fun createQueryFilter(productCategoryId: Long?, brandId: Long?, name: String?): ProductQueryFilter {
        return ProductQueryFilter(
            productCategoryId = productCategoryId,
            brandId = brandId,
            name = name
        )
    }

    fun createPaginationFilter(skipCount: Long?, limitCount: Long?): Pagination {
        val skip = skipCount ?: 0
        val limit = limitCount ?: 50
        return Pagination(
            skip = skip,
            limit = limit
        )
    }
}
