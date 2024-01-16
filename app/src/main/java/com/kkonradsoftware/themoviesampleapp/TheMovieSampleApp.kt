package com.kkonradsoftware.themoviesampleapp

import android.app.Application
import com.kkonradsoftware.feature.domain.usecase.GetMovieNowPlayingUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TheMovieSampleApp : Application() {

    @Inject
    lateinit var getMovieNowPlayingUseCase: GetMovieNowPlayingUseCase

}