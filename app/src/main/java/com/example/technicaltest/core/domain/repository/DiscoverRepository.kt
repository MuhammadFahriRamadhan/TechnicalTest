package com.example.technicaltest.core.domain.repository

import com.example.technicaltest.core.domain.model.DiscoverMovie
import io.reactivex.Single

interface DiscoverRepository {
    fun getMovies(apiKey : String,page : Int) : Single<DiscoverMovie>
}