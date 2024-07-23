package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.CreateUserRequestDto

interface CreateUserValidatable {

    fun validate(request: CreateUserRequestDto)
}
