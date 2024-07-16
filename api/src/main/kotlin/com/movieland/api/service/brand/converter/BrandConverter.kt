package com.movieland.api.service.brand.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.brand.BrandResponseDto
import com.movieland.api.dto.brand.CreateBrandRequestDto
import com.movieland.domain.entity.brand.Brand
import org.springframework.stereotype.Component

@Component
class BrandConverter(
    private val tsidFactory: TsidFactory
) {

    fun convert(request: CreateBrandRequestDto): Brand {
        return Brand(
            name = request.name,
            link = request.link
        )
    }

    fun convert(entity: Brand): BrandResponseDto {
        return BrandResponseDto(
            id = entity.id!!,
            name = entity.name,
            link = entity.link,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}
