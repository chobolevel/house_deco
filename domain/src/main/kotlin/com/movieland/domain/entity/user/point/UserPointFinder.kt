package com.movieland.domain.entity.user.point

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.point.QUserPoint.userPoint
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserPointFinder(
    private val repository: UserPointRepository,
    private val customRepository: UserPointCustomRepository
) {

    fun findById(id: String): UserPoint {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_POINT_IS_NOT_FOUND,
            message = "해당 유저 포인트 내역을 찾을 수 없습니다."
        )
    }

    fun findByIdAndUserId(id: String, userId: String): UserPoint {
        return repository.findByIdAndUserId(id, userId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_POINT_IS_NOT_FOUND,
            message = "해당 유저 포인트 내역을 찾을 수 없습니다."
        )
    }

    fun search(
        queryFilter: UserPointQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserPointOrderType>?
    ): List<UserPoint> {
        val orderSpecifiers = orderSpecifiers(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: UserPointQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    private fun orderSpecifiers(orderTypes: List<UserPointOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                UserPointOrderType.CREATED_AT_ASC -> userPoint.createdAt.asc()
                UserPointOrderType.CREATED_AT_DESC -> userPoint.createdAt.desc()
            }
        }.toTypedArray()
    }
}
