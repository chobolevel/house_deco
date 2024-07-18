package com.movieland.domain.entity.user

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.QUser.user
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserCustomRepository : QuerydslRepositorySupport(User::class.java) {

    fun findById(id: Long): User? {
        return from(user)
            .where(user.id.eq(id))
            .fetchOne()
    }

    fun findByPredicates(
        predicates: List<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: List<OrderSpecifier<*>>
    ): List<User> {
        return from(user)
            .where(*predicates.toTypedArray())
            .orderBy(*orderSpecifiers.toTypedArray())
            .offset(pagination.skip)
            .limit(pagination.limit)
            .fetch()
    }

    fun countByPredicates(predicates: List<BooleanExpression>): Long {
        return from(user)
            .where(*predicates.toTypedArray())
            .fetchCount()
    }
}
