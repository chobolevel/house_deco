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
    INVALID_USER_LOGIN_TYPE,

    // USER_IMAGE
    USER_IMAGE_IS_NOT_FOUND,

    // USER_POINT
    USER_POINT_IS_NOT_FOUND,

    // PRODUCT_CATEGORY
    PRODUCT_CATEGORY_IS_NOT_FOUND
}
