package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.point.UpdateUserPointRequestDto

interface UpdateUserPointValidatable {

    fun validate(request: UpdateUserPointRequestDto)
}
