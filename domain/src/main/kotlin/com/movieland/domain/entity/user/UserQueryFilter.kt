package com.movieland.domain.entity.user

import com.movieland.domain.entity.user.QUser.user
import com.querydsl.core.types.dsl.BooleanExpression

data class UserQueryFilter(
    val email: String?,
) {

    fun toPredicates(): List<BooleanExpression> {
        return listOfNotNull(
            email?.let { user.email.eq(it) }
        )
    }
}
