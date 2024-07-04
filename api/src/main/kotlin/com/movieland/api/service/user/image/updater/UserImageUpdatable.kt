package com.movieland.api.service.user.image.updater

import com.movieland.api.dto.user.image.UpdateUserProfileImageRequestDto
import com.movieland.domain.entity.user.image.UserImage

interface UserImageUpdatable {

    fun markAsUpdate(request: UpdateUserProfileImageRequestDto, userImage: UserImage): UserImage

    fun order(): Int
}
