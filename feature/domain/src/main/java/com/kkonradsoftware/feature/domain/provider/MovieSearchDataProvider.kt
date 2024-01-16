package com.kkonradsoftware.feature.domain.provider

import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.NetworkResult
import com.kkonradsoftware.feature.domain.model.SearchResponseModel

interface MovieSearchDataProvider {
    suspend fun searchMovie(
        search: String,
    ): NetworkResult<DomainError, SearchResponseModel>
}