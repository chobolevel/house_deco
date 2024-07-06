package com.movieland.api.service.user.point

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.user.point.CreateUserPointRequestDto
import com.movieland.api.dto.user.point.UpdateUserPointRequestDto
import com.movieland.api.dto.user.point.UserPointResponseDto
import com.movieland.api.service.user.point.converter.UserPointConverter
import com.movieland.api.service.user.point.updater.UserPointUpdatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.point.UserPointFinder
import com.movieland.domain.entity.user.point.UserPointOrderType
import com.movieland.domain.entity.user.point.UserPointQueryFilter
import com.movieland.domain.entity.user.point.UserPointRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserPointService(
    private val repository: UserPointRepository,
    private val finder: UserPointFinder,
    private val converter: UserPointConverter,
    private val updaters: List<UserPointUpdatable>,
    private val userFinder: UserFinder
) {

    fun createUserPoint(userId: String, request: CreateUserPointRequestDto): String {
        val user = userFinder.findById(userId)
        val userPoint = converter.convert(request).also {
            it.setBy(user)
        }
        user.point += userPoint.currency
        return repository.save(userPoint).id!!
    }

    fun searchUserPoints(
        queryFilter: UserPointQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserPointOrderType>?
    ): PaginationResponseDto {
        val userPoints = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = userPoints.map { converter.convert(it) },
            totalCount = totalCount
        )
    }

    fun fetchUserPoint(userId: String, id: String): UserPointResponseDto {
        val userPoint = finder.findByIdAndUserId(id, userId)
        return converter.convert(userPoint)
    }

    fun updateUserPoint(userId: String, id: String, request: UpdateUserPointRequestDto): String {
        val userPoint = finder.findByIdAndUserId(id, userId)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, userPoint) }
        return userPoint.id!!
    }

    fun deleteUserPoint(userId: String, id: String): Boolean {
        val userPoint = finder.findByIdAndUserId(id, userId)
        userPoint.delete()
        return true
    }

}
