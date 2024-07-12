package com.movieland.api.dto.brand

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.brand.BrandUpdateMask
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateBrandRequestDto(
    @field:NotNull(message = "브랜드 이름은 필수 값입니다.")
    val name: String,
    @field:NotNull(message = "브랜드 바로가기 링크는 필수 값입니다.")
    val link: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateBrandRequestDto(
    val name: String?,
    val link: String?,
    @field:NotEmpty(message = "update_mask는 필수 값입니다.")
    val updateMask: List<BrandUpdateMask>
)
