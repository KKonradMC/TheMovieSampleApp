package com.kkonradsoftware.feature.data.dto

import com.google.gson.annotations.SerializedName
import com.kkonradsoftware.common.domain.const.NO_INT_VALUE
import com.kkonradsoftware.feature.domain.model.SearchResponseModel

class SearchResponseDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<ResultDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?,
) {
    fun toDomain() = SearchResponseModel(
        page = page ?: NO_INT_VALUE,
        results = results?.map(ResultDto::toDomain) ?: emptyList(),
        totalPages = totalPages ?: NO_INT_VALUE,
        totalResults = totalResults ?: NO_INT_VALUE,
    )
}
