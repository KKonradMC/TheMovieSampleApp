package com.kkonradsoftware.feature.presentation.list.model

import com.kkonradsoftware.feature.domain.model.ResultModel
import com.kkonradsoftware.libraries.ds.listitem.ListItemData
import com.kkonradsoftware.libraries.ds.rate.RateViewData

data class MovieListItemData(
    val result: ResultModel,
    val isFavourite: Boolean,
    val onFavouriteClick: (Int) -> Unit,
    val onItemClick: (ResultModel) -> Unit,
) {
    val id = result.id
    val listItemData: ListItemData = ListItemData(
        id = result.id,
        title = result.title,
        imagePath = result.posterPath,
        voteLabel = "${result.voteAverage} (${result.voteCount})",
        rateViewData = RateViewData(value = result.voteAverage),
        isFavourite = isFavourite,
        onFavouriteClick = onFavouriteClick,
    )
}