package com.movieland.api.advice

import com.movieland.api.dto.common.ErrorResponse
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import com.movieland.domain.exception.PolicyException
import com.movieland.domain.exception.UnAuthorizedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
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

    // authentication exception
    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.BAD_CREDENTIALS
        val errorMessage = e.message.toString()
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(errorCode, errorMessage))
    }

    @ExceptionHandler(UnAuthorizedException::class)
    fun handleUnAuthorizedException(e: UnAuthorizedException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        val errorMessage = e.message
        return ResponseEntity.status(e.status).body(ErrorResponse(errorCode, errorMessage))
    }

    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFoundException(e: DataNotFoundException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        val errorMessage = e.message
        return ResponseEntity.status(e.status).body(ErrorResponse(errorCode, errorMessage))
    }

    @ExceptionHandler(PolicyException::class)
    fun handlePolicyException(e: PolicyException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        val errorMessage = e.message
        return ResponseEntity.status(e.status).body(ErrorResponse(errorCode, errorMessage))
    }
}
