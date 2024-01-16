package com.kkonradsoftware.feature.data.repository

import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.Empty
import com.kkonradsoftware.common.domain.model.Failure
import com.kkonradsoftware.common.domain.model.NetworkResult
import com.kkonradsoftware.common.domain.model.Success
import com.kkonradsoftware.feature.data.client.MovieDbClient
import com.kkonradsoftware.feature.domain.model.NowPlayingResponseModel
import com.kkonradsoftware.feature.domain.model.SearchResponseModel
import com.kkonradsoftware.feature.domain.provider.MovieNowPlayingDataProvider
import com.kkonradsoftware.feature.domain.provider.MovieSearchDataProvider
import com.kkonradsoftware.libraries.network.NetworkConnectionSupporter

interface MovieDbDataRepository : MovieNowPlayingDataProvider, MovieSearchDataProvider

internal class MovieDbDataRepositoryImpl(
    private val networkConnectionSupporter: NetworkConnectionSupporter,
) : MovieDbDataRepository {

    private val movieDbClient = networkConnectionSupporter.createClient(
        clazz = MovieDbClient::class.java,
    )

    override suspend fun getMovieNowPlaying(
        language: String,
        page: Int,
    ): NetworkResult<DomainError, NowPlayingResponseModel> =
        networkConnectionSupporter.call {
            movieDbClient.getMovieNowPlaying(
                language = language,
                page = page,
            )
        }.execute(
            onSuccess = { it.toDomain().let(::Success) },
            onEmpty = { Empty },
            onFailure = ::Failure,
        )

    override suspend fun searchMovie(
        search: String,
    ): NetworkResult<DomainError, SearchResponseModel> =
        networkConnectionSupporter.call {
            movieDbClient.getSearchMovie(
                query = search,
            )
        }.execute(
            onSuccess = { it.toDomain().let(::Success) },
            onEmpty = { Empty },
            onFailure = ::Failure,
        )
}