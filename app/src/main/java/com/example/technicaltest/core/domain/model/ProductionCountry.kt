package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountry(
    val iso31661: String?,
    val name: String?
) : Parcelable