package com.movieland.domain.entity.user.point

import com.movieland.domain.entity.user.point.QUserPoint.userPoint
import com.querydsl.core.types.dsl.BooleanExpression

class UserPointQueryFilter(
    val userId: Long?,
    val type: UserPointType?
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            userId?.let { userPoint.user.id.eq(it) },
            type?.let { userPoint.type.eq(it) }
        ).toTypedArray()
    }
}
