package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.UpdateUserRequestDto

interface UpdateUserValidatable {

    fun validate(request: UpdateUserRequestDto)
}
