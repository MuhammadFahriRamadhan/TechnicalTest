package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultTrailer(
    val id: String?,
    val iso31661: String?,
    val iso6391: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
) : Parcelable