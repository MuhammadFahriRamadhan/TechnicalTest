package com.example.technicaltest.core.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.technicaltest.R
import com.example.technicaltest.core.di.GlideApp

fun ImageView.loadImage(
    path: String,
    @DrawableRes placeholder: Int = R.drawable.ic_img_placeholder
) {
    GlideApp.with(this)
        .load("https://image.tmdb.org/t/p/w185"+path)
        .placeholder(placeholder)
        .into(this)
}