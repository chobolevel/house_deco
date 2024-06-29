package com.movieland.domain.exception

enum class ErrorCode {
    INVALID_PARAMETER,

    // USER
    USER_IS_NOT_FOUND,
    EMAIL_ALREADY_EXISTS,
    NICKNAME_ALREADY_EXISTS,
    PHONE_ALREADY_EXISTS,
    INVALID_USER_LOGIN_TYPE
}
