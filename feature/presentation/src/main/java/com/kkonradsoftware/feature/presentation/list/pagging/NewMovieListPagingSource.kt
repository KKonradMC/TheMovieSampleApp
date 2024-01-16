package com.kkonradsoftware.feature.presentation.list.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kkonradsoftware.feature.domain.model.ResultModel
import com.kkonradsoftware.feature.domain.usecase.GetMovieNowPlayingUseCase
import javax.inject.Inject

class NewMovieListPagingSource @Inject constructor(
    private val getMovieNowPlayingUseCase: GetMovieNowPlayingUseCase,
) : PagingSource<Int, ResultModel>() {
    override fun getRefreshKey(state: PagingState<Int, ResultModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultModel> {
        val nextPageNumber = params.key ?: 1
        return getMovieNowPlayingUseCase(
            params = GetMovieNowPlayingUseCase.Params(
                page = nextPageNumber,
            ),
        ).suspendExecute(
            onSuccess = {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = nextPageNumber + 1
                )
            },
            onFailure = {
                LoadResult.Error(Throwable())
            }
        )
    }
}