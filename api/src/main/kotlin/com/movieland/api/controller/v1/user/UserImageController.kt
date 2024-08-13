package com.movieland.api.controller.v1.user

import com.movieland.api.annorations.HasAuthorityUser
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.user.image.CreateUserProfileImageRequestDto
import com.movieland.api.dto.user.image.UpdateUserProfileImageRequestDto
import com.movieland.api.service.user.UserImageService
import com.movieland.domain.getUserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Tag(name = "UserImage (유저 이미지)", description = "유저 이미지 API")
@RestController
@RequestMapping("/api/v1/users")
class UserImageController(
    private val service: UserImageService
) {

    @HasAuthorityUser
    @Operation(summary = "유저 프로필 이미지 등록 API")
    @PostMapping("/profile-image")
    fun createUserProfileImage(
        principal: Principal,
        @Valid
        @RequestBody
        request: CreateUserProfileImageRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createUserProfileImage(principal.getUserId(), request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "유저 프로필 이미지 수정 API")
    @PutMapping("/profile-image/{id}")
    fun updateUserProfileImage(
        @PathVariable id: Long,
        principal: Principal,
        @Valid
        @RequestBody
        request: UpdateUserProfileImageRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateUserProfileImage(id, principal.getUserId(), request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "유저 프로필 이미지 삭제 API")
    @DeleteMapping("/profile-image/{id}")
    fun deleteUserProfileImage(@PathVariable id: Long, principal: Principal): ResponseEntity<ResultResponse> {
        val result = service.deleteUserProfileImage(id, principal.getUserId())
        return ResponseEntity.ok(ResultResponse(result))
    }
}
