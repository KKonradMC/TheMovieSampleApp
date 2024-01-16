package com.kkonradsoftware.feature.domain.model


data class NowPlayingResponseModel(
    val dates: DateModel,
    val page: Int,
    val results: List<ResultModel>,
    val totalPages: Int,
    val totalResults: Int?
)
