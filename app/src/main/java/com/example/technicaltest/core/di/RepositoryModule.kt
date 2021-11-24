package com.example.technicaltest.core.di

import com.example.technicaltest.core.data.network.api.DiscoverApi
import com.example.technicaltest.core.data.network.api.MovieApi
import com.example.technicaltest.core.data.network.repositoryimpl.DiscoverRepositoryImpl
import com.example.technicaltest.core.data.network.repositoryimpl.MovieRepositoryImpl
import com.example.technicaltest.core.domain.repository.DiscoverRepository
import com.example.technicaltest.core.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideDiscoverRepository(discoverApi: DiscoverApi) : DiscoverRepository {
        return DiscoverRepositoryImpl(discoverApi)
    }

    @Provides
    fun provideMovieRepository(movieApi: MovieApi) : MovieRepository{
        return MovieRepositoryImpl(movieApi)
    }
}