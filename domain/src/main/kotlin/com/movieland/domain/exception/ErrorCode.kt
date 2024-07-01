package com.movieland.domain.exception

enum class ErrorCode {
    // common
    INVALID_PARAMETER,

    // token
    EXPIRED_TOKEN,
    INVALID_TOKEN,

    // auth
    BAD_CREDENTIALS,

    // USER
    USER_IS_NOT_FOUND,
    EMAIL_ALREADY_EXISTS,
    NICKNAME_ALREADY_EXISTS,
    PHONE_ALREADY_EXISTS,
    INVALID_USER_LOGIN_TYPE
}
