package com.kkonradsoftware.feature.data.di

import android.content.Context
import android.content.SharedPreferences
import com.kkonradsoftware.feature.data.repository.FavouriteRepository
import com.kkonradsoftware.feature.data.repository.FavouriteRepositoryImpl
import com.kkonradsoftware.feature.data.repository.MovieDbDataRepository
import com.kkonradsoftware.feature.data.repository.MovieDbDataRepositoryImpl
import com.kkonradsoftware.feature.domain.provider.FavouriteDataProvider
import com.kkonradsoftware.feature.domain.provider.MovieNowPlayingDataProvider
import com.kkonradsoftware.feature.domain.provider.MovieSearchDataProvider
import com.kkonradsoftware.feature.domain.writer.FavouriteDataWriter
import com.kkonradsoftware.libraries.network.NetworkConnectionSupporter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @FavouriteSharedPrefs
    fun provideFavouriteSharedPrefs(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(
        FAVOURITE_SHARE_PREFS,
        Context.MODE_PRIVATE
    )

    @Provides
    fun provideFavouriteRepository(
        @FavouriteSharedPrefs sharedPreferences: SharedPreferences,
    ): FavouriteRepository = FavouriteRepositoryImpl(
        sharedPreferences = sharedPreferences,
    )

    @Provides
    fun provideMovieDbDataRepository(
        networkConnectionSupporter: NetworkConnectionSupporter,
    ): MovieDbDataRepository = MovieDbDataRepositoryImpl(
        networkConnectionSupporter = networkConnectionSupporter,
    )
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModuleBinder {

    @Binds
    abstract fun bindFavouriteDataProvider(
        repository: FavouriteRepository,
    ): FavouriteDataProvider

    @Binds
    abstract fun bindFavouriteDataWriter(
        repository: FavouriteRepository,
    ): FavouriteDataWriter

    @Binds
    abstract fun bindMovieNowPlayingDataProvider(
        repository: MovieDbDataRepository,
    ): MovieNowPlayingDataProvider

    @Binds
    abstract fun bindMovieSearchDataProvider(
        repository: MovieDbDataRepository,
    ): MovieSearchDataProvider
}

private const val FAVOURITE_SHARE_PREFS = "FAVOURITE_SHARE_PREFS"