package com.movieland.api.service.user.query

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.product.UserProductCouponQueryFilter
import org.springframework.stereotype.Component

@Component
class UserProductCouponQueryCreator {

    fun createQueryFilter(userId: Long?): UserProductCouponQueryFilter {
        return UserProductCouponQueryFilter(
            userId = userId
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
