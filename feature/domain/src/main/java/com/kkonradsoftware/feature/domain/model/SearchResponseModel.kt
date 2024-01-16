package com.kkonradsoftware.feature.domain.model

data class SearchResponseModel(
    val page: Int,
    val results: List<ResultModel>,
    val totalPages: Int,
    val totalResults: Int,
)