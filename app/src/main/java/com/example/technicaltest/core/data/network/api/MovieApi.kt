package com.example.technicaltest.core.data.network.api

import com.example.technicaltest.core.data.response.DetailMovieResponse
import com.example.technicaltest.core.data.response.DiscoverMovieResponse
import com.example.technicaltest.core.data.response.ReviewResponse
import com.example.technicaltest.core.data.response.TrailerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId : String,
        @Query("api_key") apiKey : String
    ) : Single<DetailMovieResponse>

    @GET("movie/{movieId}/videos")
    fun getTrailer(
        @Path("movieId")
        movieId : String,
        @Query("api_key")
        apikey: String
    ) : Single<TrailerResponse>

    @GET("movie/{movieId}/reviews")
    fun getReviews(
        @Path("movieId")
        movieId : String,
        @Query("api_key")
        apikey: String
    ) : Single<ReviewResponse>
}