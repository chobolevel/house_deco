package com.movieland.api.service.brand

import com.movieland.domain.Pagination
import com.movieland.domain.entity.brand.BrandQueryFilter
import org.springframework.stereotype.Component

@Component
class BrandQueryCreator {

    fun createQueryFilter(name: String?): BrandQueryFilter {
        return BrandQueryFilter(
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
