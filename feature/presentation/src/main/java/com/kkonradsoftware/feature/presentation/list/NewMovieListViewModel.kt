package com.kkonradsoftware.feature.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.kkonradsoftware.feature.domain.model.ResultModel
import com.kkonradsoftware.feature.domain.usecase.ChangeFavouriteStateUseCase
import com.kkonradsoftware.feature.domain.usecase.IsFavouriteUseCase
import com.kkonradsoftware.feature.domain.usecase.SearchMovieUseCase
import com.kkonradsoftware.feature.presentation.details.model.NewMovieDetailsModel
import com.kkonradsoftware.feature.presentation.list.model.MovieListItemData
import com.kkonradsoftware.feature.presentation.list.pagging.NewMovieListPagingSource
import com.kkonradsoftware.libraries.livedataext.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewMovieListViewModel @Inject constructor(
    private val newMovieListPagingSource: NewMovieListPagingSource,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase,
    private val changeFavouriteStateUseCase: ChangeFavouriteStateUseCase,
) : ViewModel() {

    private val _searchData = HashMap<String, List<ResultModel>>()
    private val _searchText = MutableStateFlow(value = "")

    private val _data = Pager(PagingConfig(pageSize = 20)) {
        newMovieListPagingSource
    }.flow.cachedIn(viewModelScope)

    private val _updatedFavourites = SingleLiveEvent<Unit>()
    val openDetailsSingleEvent = SingleLiveEvent<NewMovieDetailsModel>()

    val data = combine(_data, _updatedFavourites.asFlow(), _searchText) { pagingData, favouriteMap, text ->
        pagingData.filter {
            text.isEmpty() || it.title.contains(text)
        }.map {
            val isFavourite = isFavouriteUseCase(params = IsFavouriteUseCase.Params(id = it.id))
            MovieListItemData(
                result = it,
                isFavourite = isFavourite,
                onFavouriteClick = { id ->
                    viewModelScope.launch {
                        changeFavouriteStateUseCase(
                            params = ChangeFavouriteStateUseCase.Params(id = id)
                        ).let {
                            _updatedFavourites.run {
                                value = Unit
                                call()
                            }
                        }
                    }
                },
                onItemClick = {
                    openDetailsSingleEvent.apply {
                        value = NewMovieDetailsModel(
                            id = it.id,
                            imagePath = it.posterPath,
                            title = it.title,
                            releaseDate = it.releaseDate,
                            voteAverage = it.voteAverage,
                            voteCount = it.voteCount,
                            overview = it.overview,
                            isFavourite = isFavourite
                        )
                        call()
                    }
                }
            )
        }
    }.asLiveData()

    private val _searchAdapterData = MutableLiveData(emptyList<ResultModel>())
    val searchAdapterData = _searchAdapterData.map { it.map { item -> item.title } }

    fun refresh() {
        _updatedFavourites.run {
            value = Unit
            call()
        }
    }

    fun onSearchTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val query = s.toString()
        _searchText.value = query
        if (s.length == SEARCH_QUERY_LENGTH) {
            viewModelScope.launch {
                if (!_searchData.contains(query)) {
                    _searchData[query] = search(query)
                }
                _searchAdapterData.value = _searchData[query]
            }
        }
    }

    private suspend fun search(query: String) = searchMovieUseCase(
        params = SearchMovieUseCase.Params(
            search = query,
        )
    ).execute(
        onSuccess = { it.results },
        onFailure = { emptyList() }
    )
}

private const val SEARCH_QUERY_LENGTH = 4


