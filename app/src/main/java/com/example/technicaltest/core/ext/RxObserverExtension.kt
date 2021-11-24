package com.example.technicaltest.core.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

object TagInjection {
    const val UI_Scheduler = "TagInjection.UI_Scheduler"
    const val IO_Scheduler = "TagInjection.IO_Scheduler"
}