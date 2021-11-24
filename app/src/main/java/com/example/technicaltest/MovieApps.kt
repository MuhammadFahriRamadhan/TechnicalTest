package com.example.technicaltest

import com.example.technicaltest.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MovieApps : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

}