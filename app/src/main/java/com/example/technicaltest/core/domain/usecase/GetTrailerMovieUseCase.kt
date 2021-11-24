package com.example.technicaltest.core.domain.usecase

import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.Trailer
import com.example.technicaltest.core.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTrailerMovieUseCase  @Inject constructor(private val movieRepository: MovieRepository){

    fun getTrailerMovie(movieId: String,apiKey: String) : Single<Trailer> {
        return movieRepository.getTrailer(movieId,apiKey)
    }

}