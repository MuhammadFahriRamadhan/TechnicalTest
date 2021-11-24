package com.example.technicaltest.core.domain.repository

import com.example.technicaltest.core.data.response.DetailMovieResponse
import com.example.technicaltest.core.data.response.ReviewResponse
import com.example.technicaltest.core.data.response.TrailerResponse
import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.model.Trailer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRepository {

    fun getDetailMovie(movieId: String,apiKey: String) : Single<DetailMovie>

    fun getTrailer(movieId : String, apikey: String) : Single<Trailer>

    fun getReviews(movieId : String, apikey: String) : Single<Review>

}