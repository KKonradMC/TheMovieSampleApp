package com.kkonradsoftware.feature.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kkonradsoftware.feature.domain.usecase.ChangeFavouriteStateUseCase
import com.kkonradsoftware.feature.presentation.details.model.NewMovieDetailsModel
import com.kkonradsoftware.libraries.ds.R
import com.kkonradsoftware.libraries.ds.rate.RateViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewMovieDetailsViewModel @Inject constructor(
    private val changeFavouriteStateUseCase: ChangeFavouriteStateUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<NewMovieDetailsModel>()
    val imagePath = _data.map { it.imagePath }
    val title = _data.map { it.title }
    val releaseDate = _data.map { it.releaseDate }
    val rateViewData: LiveData<RateViewData> = _data.map { RateViewData(value = it.voteAverage) }
    val voteLabel = _data.map { "${it.voteAverage} (${it.voteCount})" }
    val overview = _data.map { it.overview }
    val favouriteRes: LiveData<Int> = _data.map {
        if (it.isFavourite)
            R.drawable.baseline_star_24
        else
            R.drawable.baseline_star_outline_24
    }

    fun setData(data: NewMovieDetailsModel) {
        _data.value = data
    }

    fun changeFavourite() {
        _data.value?.let {
            viewModelScope.launch {
                val isFavourite = changeFavouriteStateUseCase(
                    params = ChangeFavouriteStateUseCase.Params(
                        id = it.id,
                    )
                )
                _data.run { value = value?.copy(isFavourite = isFavourite) }
            }
        }

    }
}