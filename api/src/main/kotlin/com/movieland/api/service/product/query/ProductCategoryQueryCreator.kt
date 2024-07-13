package com.movieland.api.service.product.query

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.category.ProductCategoryQueryFilter
import org.springframework.stereotype.Component

@Component
class ProductCategoryQueryCreator {

    fun createQueryFilter(name: String?): ProductCategoryQueryFilter {
        return ProductCategoryQueryFilter(
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
