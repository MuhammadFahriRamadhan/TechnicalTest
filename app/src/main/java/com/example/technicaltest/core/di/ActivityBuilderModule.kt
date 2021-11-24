package com.example.technicaltest.core.di

import com.example.technicaltest.view.detail.DetailActivity
import com.example.technicaltest.view.main.MainActivity
import com.example.technicaltest.view.splashscreen.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun bindMovieDetailActivity(): DetailActivity


}