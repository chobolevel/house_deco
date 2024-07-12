package com.movieland.api.service.brand.updater

import com.movieland.api.dto.brand.UpdateBrandRequestDto
import com.movieland.domain.entity.brand.Brand

interface BrandUpdatable {

    fun markAsUpdate(request: UpdateBrandRequestDto, brand: Brand): Brand

    fun order(): Int

}
