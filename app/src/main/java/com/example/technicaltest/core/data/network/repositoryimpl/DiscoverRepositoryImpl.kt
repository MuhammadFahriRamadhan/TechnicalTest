package com.example.technicaltest.core.data.network.repositoryimpl

import com.example.technicaltest.core.data.network.api.DiscoverApi
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.domain.repository.DiscoverRepository
import io.reactivex.Single

class DiscoverRepositoryImpl (private val discoverApi: DiscoverApi) : DiscoverRepository {

    override fun getMovies(apiKey: String,page : Int): Single<DiscoverMovie> {
        return discoverApi.getMovies(apiKey,page).map { it.toDiscoveryMovie() }
    }

}