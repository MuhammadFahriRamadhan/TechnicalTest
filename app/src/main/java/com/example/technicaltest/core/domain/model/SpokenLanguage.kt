package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage(
    val englishName: String?,
    val iso6391: String?,
    val name: String?
) : Parcelable