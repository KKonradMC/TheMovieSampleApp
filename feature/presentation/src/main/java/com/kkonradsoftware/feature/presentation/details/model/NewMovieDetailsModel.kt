package com.kkonradsoftware.feature.presentation.details.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewMovieDetailsModel(
    val id: Int,
    val imagePath: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val isFavourite: Boolean,
) : Parcelable
