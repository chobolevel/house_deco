package com.movieland.domain.entity.user.point

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.point.QUserPoint.userPoint
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserPointCustomRepository : QuerydslRepositorySupport(UserPoint::class.java) {

    fun searchByPredicates(
        predicates: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<UserPoint> {
        return from(userPoint)
            .where(*predicates)
            .orderBy(*orderSpecifiers)
            .offset(pagination.skip)
            .limit(pagination.limit)
            .fetch()
    }

    fun countByPredicates(predicates: Array<BooleanExpression>): Long {
        return from(userPoint)
            .where(*predicates)
            .fetchCount()
    }

}
