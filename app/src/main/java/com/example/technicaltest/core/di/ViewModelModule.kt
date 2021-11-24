package com.example.technicaltest.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.technicaltest.core.di.viewmodel.ViewModelFactory
import com.example.technicaltest.core.di.viewmodel.ViewModelKey
import com.example.technicaltest.view.detail.DetailActivityVM
import com.example.technicaltest.view.main.MainActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    abstract fun bindMainActivityVM(viewModel: MainActivityVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityVM::class)
    abstract fun bindMovieDetailVM(viewModel: DetailActivityVM): ViewModel
}