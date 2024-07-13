package com.movieland.api.service.user.updater

import com.movieland.api.dto.user.point.UpdateUserPointRequestDto
import com.movieland.domain.entity.user.point.UserPoint

interface UserPointUpdatable {

    fun markAsUpdate(request: UpdateUserPointRequestDto, userPoint: UserPoint): UserPoint

    fun order(): Int
}
