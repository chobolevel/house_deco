package com.movieland.api.controller.v1.upload

import com.movieland.api.annorations.HasAuthority
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.upload.CreatePresignedUrlRequestDto
import com.movieland.api.service.upload.UploadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Upload (업로드)", description = "파일 업로드 API")
@RestController
@RequestMapping("/api/v1")
class UploadController(
    private val uploadService: UploadService
) {

    @HasAuthority
    @Operation(summary = "파일 업로드 Presigned Url 생성 API")
    @PostMapping("/upload/presigned-url")
    fun createPresignedUrl(@RequestBody request: CreatePresignedUrlRequestDto): ResponseEntity<ResultResponse> {
        val result = uploadService.getPresignedUrl(request.prefix.name, request.filename)
        return ResponseEntity.ok(ResultResponse(result))
    }
}
