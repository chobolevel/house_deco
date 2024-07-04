package com.movieland.api.service.user.image.convert

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.user.image.CreateUserProfileImageRequestDto
import com.movieland.api.dto.user.image.UserImageResponseDto
import com.movieland.domain.entity.user.image.UserImage
import com.movieland.domain.entity.user.image.UserImageType
import org.springframework.stereotype.Component
import java.time.ZoneOffset

@Component
class UserImageConverter(
    private val tsidFactory: TsidFactory
) {

    fun convert(request: CreateUserProfileImageRequestDto): UserImage {
        return UserImage(
            id = tsidFactory.create().toString(),
            originUrl = request.originUrl,
            name = request.name,
            type = UserImageType.PROFILE
        )
    }

    fun convert(entity: UserImage): UserImageResponseDto {
        return UserImageResponseDto(
            id = entity.id!!,
            originUrl = entity.originUrl,
            name = entity.name,
            type = entity.type,
            createdAt = entity.createdAt!!.toInstant(ZoneOffset.UTC).toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant(ZoneOffset.UTC).toEpochMilli()
        )
    }

}
