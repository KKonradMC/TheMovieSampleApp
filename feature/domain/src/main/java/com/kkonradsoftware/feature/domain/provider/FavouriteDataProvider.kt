package com.kkonradsoftware.feature.domain.provider

interface FavouriteDataProvider {

    suspend fun isFavourite(id: Int): Boolean
}