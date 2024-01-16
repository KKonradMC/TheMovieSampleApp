package com.kkonradsoftware.libraries.ds.listitem

import com.kkonradsoftware.libraries.ds.R
import com.kkonradsoftware.libraries.ds.rate.RateViewData

data class ListItemData(
    val id: Int,
    val title: String,
    val imagePath: String,
    val voteLabel: String,
    val rateViewData: RateViewData,
    val isFavourite: Boolean,
    val onFavouriteClick: (Int) -> Unit,
) {
    val favouriteRes = if (isFavourite)
        R.drawable.baseline_star_24
    else
        R.drawable.baseline_star_outline_24
}
