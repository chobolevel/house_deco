package com.movieland.api.controller.v1.user

import com.movieland.api.annorations.HasAuthorityAdmin
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.user.point.CreateUserPointRequestDto
import com.movieland.api.dto.user.point.UpdateUserPointRequestDto
import com.movieland.api.service.user.point.UserPointQueryCreator
import com.movieland.api.service.user.point.UserPointService
import com.movieland.domain.entity.user.point.UserPointOrderType
import com.movieland.domain.entity.user.point.UserPointType
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

@Tag(name = "UserPoint (유저 포인트)", description = "유저 포인트 API")
@RestController
@RequestMapping("/api/v1")
class UserPointController(
    private val service: UserPointService,
    private val queryCreator: UserPointQueryCreator
) {

    @HasAuthorityAdmin
    @Operation(summary = "유저 포인트 부여 API")
    @PostMapping("/users/{id}/points")
    fun createUserPoint(
        @PathVariable id: String,
        @Valid @RequestBody request: CreateUserPointRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createUserPoint(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 포인트 내역 조회 API")
    @GetMapping("/users/{id}/points")
    fun searchUserPointHistories(
        @PathVariable id: String,
        @RequestParam(required = false) type: UserPointType?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<UserPointOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(id, type)
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchUserPoints(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "유저 포인트 내역 단건 조회 API")
    @GetMapping("/users/{userId}/points/{id}")
    fun fetchUserPointHistory(@PathVariable userId: String, @PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.fetchUserPoint(
            userId = userId,
            id = id
        )
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "유저 포인트 내역 수정 API")
    @PutMapping("/users/{userId}/points/{id}")
    fun updateUserPointHistory(
        @PathVariable userId: String,
        @PathVariable id: String,
        @RequestBody request: UpdateUserPointRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateUserPoint(
            userId = userId,
            id = id,
            request = request
        )
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "유저 포인트 내역 삭제 API")
    @DeleteMapping("/users/{userId}/points/{id}")
    fun deleteUserPointHistory(@PathVariable userId: String, @PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.deleteUserPoint(
            userId = userId,
            id = id
        )
        return ResponseEntity.ok(ResultResponse(result))
    }

}
