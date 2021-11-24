package com.example.technicaltest.core.base

import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivityVM<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>(),
    HasAndroidInjector {

    protected var baseViewModel: VM? = null

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        baseViewModel = bindViewModel()
        super.onCreate(savedInstanceState)
        baseViewModel?.let { observeViewModel(it) }
    }

    abstract fun observeViewModel(viewModel: VM)

    abstract fun bindViewModel(): VM

    protected fun handleLoading(showLoading: Boolean?) {
        if (showLoading == true) showProgress() else hideProgress()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onDestroy() {
        super.onDestroy()
        baseViewModel = null
    }
}
