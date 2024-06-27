package com.movieland.domain.exception

import org.springframework.http.HttpStatus

open class CustomException(
  open val errorCode: ErrorCode,
  open val status: HttpStatus,
  override val message: String,
  open val throwable: Throwable? = null
) : RuntimeException(message, throwable)

open class CustomWarnException(
  override val errorCode: ErrorCode,
  override val status: HttpStatus,
  override val message: String,
  override val throwable: Throwable? = null
) : CustomException(errorCode, status, message, throwable)

open class CustomErrorException(
  override val errorCode: ErrorCode,
  override val status: HttpStatus,
  override val message: String,
  override val throwable: Throwable? = null
) : CustomException(errorCode, status, message, throwable)

// real custom exception

open class DataNotFoundException(
  override val errorCode: ErrorCode,
  override val status: HttpStatus = HttpStatus.NOT_FOUND,
  override val message: String,
  override val throwable: Throwable? = null
) : CustomWarnException(errorCode, status, message, throwable)
