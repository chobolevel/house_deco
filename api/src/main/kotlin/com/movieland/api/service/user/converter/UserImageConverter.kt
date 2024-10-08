package com.movieland.api.service.user.converter

import com.movieland.api.dto.user.image.CreateUserProfileImageRequestDto
import com.movieland.api.dto.user.image.UserImageResponseDto
import com.movieland.domain.entity.user.image.UserImage
import org.springframework.stereotype.Component

@Component
class UserImageConverter() {

    fun convert(request: CreateUserProfileImageRequestDto): UserImage {
        return UserImage(
            originUrl = request.originUrl,
            name = request.name,
        )
    }

    fun convert(entity: UserImage): UserImageResponseDto {
        return UserImageResponseDto(
            id = entity.id!!,
            originUrl = entity.originUrl,
            name = entity.name,
            type = entity.type,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
