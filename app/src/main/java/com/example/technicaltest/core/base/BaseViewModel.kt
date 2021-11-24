package com.example.technicaltest.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltest.core.exception.Failure
import com.example.technicaltest.core.helper.util.NetworkHandler
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val uiSchedulers: Scheduler,
    protected val ioScheduler: Scheduler,
    protected val networkHandler: NetworkHandler
) : ViewModel() {

    protected val disposable = CompositeDisposable()

    val failureLiveData: MutableLiveData<Failure> = MutableLiveData()

    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        isLoadingLiveData.value = false
        failureLiveData.value = failure
    }

    protected fun executeJob(invoke: () -> Unit) {
        when (networkHandler.isNetworkAvailable()) {
            true -> invoke()
            else -> handleFailure(Failure.NetworkConnection)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun <T> applySchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { observable ->
            observable.subscribeOn(ioScheduler).observeOn(uiSchedulers)
        }
    }
}
