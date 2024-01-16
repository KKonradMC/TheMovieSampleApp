package com.kkonradsoftware.feature.domain.writer

interface FavouriteDataWriter {
    suspend fun changeFavourite(id: Int): Boolean
}