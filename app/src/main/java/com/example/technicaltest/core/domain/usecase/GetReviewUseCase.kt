package com.example.technicaltest.core.domain.usecase

import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetReviewUseCase  @Inject constructor(private val movieRepository: MovieRepository){

    fun getReviewMovie(movieId: String,apiKey: String) : Single<Review> {
        return movieRepository.getReviews(movieId,apiKey)
    }

}