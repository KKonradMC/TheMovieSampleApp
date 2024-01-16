package com.kkonradsoftware.feature.data.repository

import android.content.SharedPreferences
import com.kkonradsoftware.feature.data.di.FavouriteSharedPrefs
import com.kkonradsoftware.feature.domain.provider.FavouriteDataProvider
import com.kkonradsoftware.feature.domain.writer.FavouriteDataWriter

interface FavouriteRepository : FavouriteDataProvider, FavouriteDataWriter

internal class FavouriteRepositoryImpl(
    @FavouriteSharedPrefs private val sharedPreferences: SharedPreferences,
) : FavouriteRepository {
    override suspend fun isFavourite(id: Int): Boolean =
        sharedPreferences.getInt("$KEY_PREFIX$id", FALSE) == TRUE

    override suspend fun changeFavourite(id: Int): Boolean {
        val isFavourite = isFavourite(id)
        sharedPreferences.edit().putInt(
            "$KEY_PREFIX$id",
            if (isFavourite) FALSE else TRUE,
        ).apply()
        return !isFavourite
    }

}

private const val KEY_PREFIX = "key_"
private const val FALSE = 0
private const val TRUE = 1