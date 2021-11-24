package com.example.technicaltest.core.di

import com.example.technicaltest.core.data.network.api.DiscoverApi
import com.example.technicaltest.core.data.network.api.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideDiscoverApi(retrofit: Retrofit) : DiscoverApi {
        return retrofit.create(DiscoverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit) : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}