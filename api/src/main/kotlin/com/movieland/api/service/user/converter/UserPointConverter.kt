package com.movieland.api.service.user.converter

import com.movieland.api.dto.user.point.CreateUserPointRequestDto
import com.movieland.api.dto.user.point.UserPointResponseDto
import com.movieland.domain.entity.user.User
import com.movieland.domain.entity.user.point.UserPoint
import org.springframework.stereotype.Component

@Component
class UserPointConverter() {

    fun convert(request: CreateUserPointRequestDto, user: User): UserPoint {
        return UserPoint(
            currency = request.currency,
            type = request.type,
            description = request.description,
        ).also {
            it.setBy(user)
        }
    }

    fun convert(entity: UserPoint): UserPointResponseDto {
        return UserPointResponseDto(
            id = entity.id!!,
            currency = entity.currency,
            type = entity.type,
            description = entity.description,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
