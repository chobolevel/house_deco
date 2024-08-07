package com.movieland.api.service.product.query

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.review.ProductReviewQueryFilter
import org.springframework.stereotype.Component

@Component
class ProductReviewQueryCreator {

    fun createQueryFilter(userId: Long?, productId: Long?): ProductReviewQueryFilter {
        return ProductReviewQueryFilter(
            userId = userId,
            productId = productId
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
