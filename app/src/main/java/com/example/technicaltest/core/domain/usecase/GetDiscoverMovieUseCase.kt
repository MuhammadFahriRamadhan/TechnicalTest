package com.example.technicaltest.core.domain.usecase

import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.domain.repository.DiscoverRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDiscoverMovieUseCase @Inject constructor(val discoverRepository: DiscoverRepository) {

    fun getMovies(apiKey : String,page : Int) : Single<DiscoverMovie> {
        return discoverRepository.getMovies(apiKey,page)
    }
}