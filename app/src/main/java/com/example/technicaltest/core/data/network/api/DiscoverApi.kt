package com.example.technicaltest.core.data.network.api

import com.example.technicaltest.core.data.response.DiscoverMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApi {
    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey : String, @Query("page") page : Int) : Single<DiscoverMovieResponse>
}