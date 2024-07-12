package com.movieland.api.service.brand.updater

import com.movieland.api.dto.brand.UpdateBrandRequestDto
import com.movieland.domain.entity.brand.Brand
import com.movieland.domain.entity.brand.BrandUpdateMask
import org.springframework.stereotype.Component

@Component
class BrandUpdater : BrandUpdatable {
    override fun markAsUpdate(request: UpdateBrandRequestDto, brand: Brand): Brand {
        request.updateMask.forEach {
            when (it) {
                BrandUpdateMask.NAME -> brand.name = request.name!!
                BrandUpdateMask.LINK -> brand.link = request.link!!
            }
        }
        return brand
    }

    override fun order(): Int {
        return 0
    }
}
