package com.movieland.api.dto.common

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PaginationResponse(
    val totalCount: Long,
    val skipCount: Long,
    val limitCount: Long,
    val data: List<Any>
)
