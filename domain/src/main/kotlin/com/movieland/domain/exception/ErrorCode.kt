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
    PRODUCT_CATEGORY_IS_NOT_FOUND,
    PRODUCT_CATEGORY_ID_IS_NULL,
    INVALID_PRODUCT_CATEGORY_PARENT,
    INVALID_PRODUCT_CATEGORY_NAME,

    // BRAND
    BRAND_IS_NOT_FOUND,
    BRAND_ID_IS_NULL,
    INVALID_BRAND_NAME,
    INVALID_BRAND_LINK,

    // PRODUCT_STATUS
    PRODUCT_STATUS_IS_NULL,

    // PRODUCT_NAME
    PRODUCT_NAME_IS_NULL,

    // PRODUCT
    PRODUCT_IS_NOT_FOUND,

    // PRODUCT_IMAGE
    PRODUCT_IMAGE_IS_NOT_FOUND,
    PRODUCT_IMAGES_ARE_EMPTY,

    // PRODUCT_OPTION
    PRODUCT_OPTION_IS_NOT_FOUND,
    PRODUCT_OPTION_IS_NOT_DELETABLE,
    INVALID_PRODUCT_OPTION_NAME,
    INVALID_PRODUCT_OPTION_ORIGINAL_PRICE,
    INVALID_PRODUCT_OPTION_SALE_PRICE,
    INVALID_PRODUCT_OPTION_STOCK,
    INVALID_PRODUCT_OPTION_ORDER
}
