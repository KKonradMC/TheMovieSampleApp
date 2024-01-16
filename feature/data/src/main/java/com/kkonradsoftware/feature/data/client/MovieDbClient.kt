package com.kkonradsoftware.feature.data.client

import com.kkonradsoftware.feature.data.dto.NowPlayingResponseDto
import com.kkonradsoftware.feature.data.dto.SearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbClient {

    @GET("/3/movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<NowPlayingResponseDto>

    @GET("/3/search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
    ): Response<SearchResponseDto>
}
