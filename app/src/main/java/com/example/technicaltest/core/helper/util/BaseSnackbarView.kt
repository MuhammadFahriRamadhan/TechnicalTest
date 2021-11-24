package com.example.technicaltest.core.helper.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.technicaltest.R
import com.google.android.material.snackbar.ContentViewCallback

class BaseSnackbarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defaultStyle), ContentViewCallback {

    init {
        View.inflate(context, R.layout.base_snackbar, this)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
    }

    override fun animateContentOut(delay: Int, duration: Int) {
    }

}