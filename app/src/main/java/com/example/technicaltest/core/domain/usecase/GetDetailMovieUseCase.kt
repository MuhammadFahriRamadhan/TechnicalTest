package com.example.technicaltest.core.domain.usecase

import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val movieRepository: MovieRepository){

    fun getDetailMovie(movieId: String,apiKey: String) : Single<DetailMovie>{
        return movieRepository.getDetailMovie(movieId,apiKey)
    }

}