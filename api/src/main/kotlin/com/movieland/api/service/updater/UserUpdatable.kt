package com.movieland.api.service.updater

import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.domain.entity.user.User

interface UserUpdatable {

    fun markAsUpdate(request: UpdateUserRequestDto, user: User): User

    fun order(): Int
}
