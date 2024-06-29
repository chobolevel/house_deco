package com.movieland.api.advice

import com.movieland.api.dto.common.ErrorResponse
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    // validation lib exception handler
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.INVALID_PARAMETER
        val message = e.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "유효하지 않은 파라미터"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(errorCode, message))
    }

    // custom exception handler
    @ExceptionHandler(ParameterInvalidException::class)
    fun handleParameterInvalidException(e: ParameterInvalidException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        val errorMessage = e.message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(errorCode, errorMessage))
    }
}
