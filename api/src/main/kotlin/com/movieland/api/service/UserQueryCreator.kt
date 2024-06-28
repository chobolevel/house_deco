package com.movieland.api.service

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.UserQueryFilter
import org.springframework.stereotype.Component

@Component
class UserQueryCreator {

    fun createQueryFilter(email: String?): UserQueryFilter {
        return UserQueryFilter(
            email = email
        )
    }

    fun createPaginationFilter(skipCount: Long?, limitCount: Long?): Pagination {
        val skip = skipCount ?: 0
        val limit = limitCount ?: 50
        return Pagination(skip, limit)
    }
}
