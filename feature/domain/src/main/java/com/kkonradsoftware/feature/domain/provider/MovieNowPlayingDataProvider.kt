package com.kkonradsoftware.feature.domain.provider

import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.NetworkResult
import com.kkonradsoftware.feature.domain.model.NowPlayingResponseModel

interface MovieNowPlayingDataProvider {
    suspend fun getMovieNowPlaying(
        language: String,
        page: Int,
    ): NetworkResult<DomainError, NowPlayingResponseModel>
}