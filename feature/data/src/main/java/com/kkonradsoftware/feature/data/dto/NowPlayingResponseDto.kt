package com.kkonradsoftware.feature.data.dto

import com.google.gson.annotations.SerializedName
import com.kkonradsoftware.common.domain.const.DASH
import com.kkonradsoftware.common.domain.const.NO_INT_VALUE
import com.kkonradsoftware.feature.domain.model.DateModel
import com.kkonradsoftware.feature.domain.model.NowPlayingResponseModel

class NowPlayingResponseDto(
    @SerializedName("dates") val dates: DateDto?,
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<ResultDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?,
) {
    fun toDomain() = NowPlayingResponseModel(
        dates = dates?.toDomain() ?: DateModel(
            minimum = DASH,
            maximum = DASH,
        ),
        page = page ?: NO_INT_VALUE,
        results = results?.map(ResultDto::toDomain) ?: emptyList(),
        totalPages = totalPages ?: 0,
        totalResults = totalResults ?: 0,
    )
}
