package com.movieland.api.controller.user

import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.api.dto.user.LoginRequestDto
import com.movieland.api.dto.user.ReissueRequestDto
import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.api.service.user.UserQueryCreator
import com.movieland.api.service.user.UserService
import com.movieland.domain.entity.user.UserOrderType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User (회원)", description = "회원 관리 API")
@RestController
@RequestMapping("/api/v1")
class UserController(
    private val userService: UserService,
    private val userQueryCreator: UserQueryCreator
) {

    @Operation(summary = "유저 회원 가입 API")
    @PostMapping("/users")
    fun createUser(
        @Valid
        @RequestBody
        request: CreateUserRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = userService.createUser(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 로그인 API")
    @PostMapping("/users/login")
    fun loginUser(
        @Valid
        @RequestBody
        request: LoginRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = userService.login(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 토큰 재발급 API")
    @PostMapping("/users/reissue")
    fun reissueUser(
        @Valid
        @RequestBody
        request: ReissueRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = userService.reissue(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 목록 조회 API")
    @GetMapping("/users")
    fun searchUser(
        @RequestParam(required = false) email: String?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<UserOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = userQueryCreator.createQueryFilter(email)
        val pagination = userQueryCreator.createPaginationFilter(skipCount, limitCount)
        val result = userService.searchUser(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 단건 조회 API")
    @GetMapping("/users/{id}")
    fun fetchUser(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = userService.fetchUser(id)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 정보 수정 API")
    @PutMapping("/users/{id}")
    fun updateUser(
        @PathVariable id: String,
        @RequestBody request: UpdateUserRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = userService.updateUser(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 회원 탈퇴 API")
    @DeleteMapping("/users/{id}")
    fun resignUser(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = userService.deleteUser(id)
        return ResponseEntity.ok(ResultResponse(result))
    }
}
