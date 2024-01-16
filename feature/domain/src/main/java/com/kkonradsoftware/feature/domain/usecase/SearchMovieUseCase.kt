package com.kkonradsoftware.feature.domain.usecase

import com.kkonradsoftware.common.domain.DomainResultUseCase
import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.DomainResult
import com.kkonradsoftware.common.domain.model.Empty
import com.kkonradsoftware.common.domain.model.Failure
import com.kkonradsoftware.common.domain.model.Success
import com.kkonradsoftware.feature.domain.model.SearchResponseModel
import com.kkonradsoftware.feature.domain.provider.MovieSearchDataProvider
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val movieSearchDataProvider: MovieSearchDataProvider,
) : DomainResultUseCase<SearchMovieUseCase.Params, SearchResponseModel> {
    data class Params(
        val search: String,
    )

    override suspend fun invoke(
        params: Params,
    ): DomainResult<DomainError, SearchResponseModel> {
        val result = movieSearchDataProvider.searchMovie(
            search = params.search,
        )
        return when (result) {
            Empty -> Failure(value = DomainError.InvalidData)
            is Failure -> result
            is Success -> result
        }
    }
}