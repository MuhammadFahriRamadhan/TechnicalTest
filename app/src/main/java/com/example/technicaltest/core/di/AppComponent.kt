package com.example.technicaltest.core.di

import com.example.technicaltest.MovieApps
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<MovieApps> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApps): Builder

        fun build(): AppComponent
    }
}