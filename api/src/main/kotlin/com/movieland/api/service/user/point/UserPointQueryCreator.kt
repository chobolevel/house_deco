package com.movieland.api.service.user.point

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.point.UserPointQueryFilter
import com.movieland.domain.entity.user.point.UserPointType
import org.springframework.stereotype.Component

@Component
class UserPointQueryCreator {

    fun createQueryFilter(userId: String?, type: UserPointType?): UserPointQueryFilter {
        return UserPointQueryFilter(
            userId = userId,
            type = type
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
