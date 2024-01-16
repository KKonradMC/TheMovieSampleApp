package com.kkonradsoftware.feature.domain.usecase

import com.kkonradsoftware.common.domain.DomainResultUseCase
import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.DomainResult
import com.kkonradsoftware.common.domain.model.Empty
import com.kkonradsoftware.common.domain.model.Failure
import com.kkonradsoftware.common.domain.model.Success
import com.kkonradsoftware.feature.domain.model.NowPlayingResponseModel
import com.kkonradsoftware.feature.domain.provider.MovieNowPlayingDataProvider
import javax.inject.Inject

class GetMovieNowPlayingUseCase @Inject constructor(
    private val movieNowPlayingDataProvider: MovieNowPlayingDataProvider,
) : DomainResultUseCase<GetMovieNowPlayingUseCase.Params, NowPlayingResponseModel> {
    data class Params(
        val language: String = "US-en",
        val page: Int,
    )

    override suspend fun invoke(
        params: Params,
    ): DomainResult<DomainError, NowPlayingResponseModel> {
        val result = movieNowPlayingDataProvider.getMovieNowPlaying(
            language = params.language,
            page = params.page,
        )
        return when (result) {
            Empty -> Failure(value = DomainError.InvalidData)
            is Failure -> result
            is Success -> result.run {
                copy(
                    value = value.copy(
                        results = value.results
                    )
                )
            }
        }
    }


}