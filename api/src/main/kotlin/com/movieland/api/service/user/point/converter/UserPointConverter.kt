package com.movieland.api.service.user.point.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.user.point.CreateUserPointRequestDto
import com.movieland.api.dto.user.point.UserPointResponseDto
import com.movieland.domain.entity.user.point.UserPoint
import org.springframework.stereotype.Component
import java.time.ZoneOffset

@Component
class UserPointConverter(
    private val tsidFactory: TsidFactory
) {

    fun convert(request: CreateUserPointRequestDto): UserPoint {
        return UserPoint(
            id = tsidFactory.create().toString(),
            currency = request.currency,
            type = request.type,
            description = request.description,
        )
    }

    fun convert(entity: UserPoint): UserPointResponseDto {
        return UserPointResponseDto(
            id = entity.id!!,
            currency = entity.currency,
            type = entity.type,
            description = entity.description,
            createdAt = entity.createdAt!!.toInstant(ZoneOffset.UTC).toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant(ZoneOffset.UTC).toEpochMilli()
        )
    }
}
