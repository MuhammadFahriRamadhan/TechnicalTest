package com.example.technicaltest.core.ext

import android.view.View
import android.widget.Button
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun Button.changeStateButton(state: Boolean) {
    this.isEnabled = state
}

// using throttleFirst for preventing multiple click
fun View.onSingleClick(timeMillis: Long = 600): Observable<Unit> {
    return this.clicks().throttleFirst(timeMillis, TimeUnit.MILLISECONDS)
}