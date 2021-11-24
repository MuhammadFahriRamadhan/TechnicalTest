package com.example.technicaltest.core.di

import android.content.Context
import com.example.technicaltest.MovieApps
import com.example.technicaltest.core.ext.TagInjection
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class
    ]
)
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: MovieApps): Context {
        return application
    }

    @Named(TagInjection.IO_Scheduler)
    @Singleton
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Named(TagInjection.UI_Scheduler)
    @Provides
    @Singleton
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

}