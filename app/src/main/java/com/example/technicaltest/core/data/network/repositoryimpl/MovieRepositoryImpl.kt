package com.example.technicaltest.core.data.network.repositoryimpl

import com.example.technicaltest.core.data.network.api.MovieApi
import com.example.technicaltest.core.data.response.ReviewResponse
import com.example.technicaltest.core.data.response.TrailerResponse
import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.model.Trailer
import com.example.technicaltest.core.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl(private val movieApi : MovieApi) : MovieRepository {
    override fun getDetailMovie(movieId: String,apiKey: String): Single<DetailMovie> {
        return movieApi.getDetailMovie(movieId,apiKey).map { it.toDetailMovie() }
    }

    override fun getTrailer(movieId: String, apikey: String): Single<Trailer> {
        return movieApi.getTrailer(movieId,apikey).map { it.toTrailer() }
    }

    override fun getReviews(movieId: String, apikey: String): Single<Review> {
        return  movieApi.getReviews(movieId,apikey).map { it.toReview() }
    }
}