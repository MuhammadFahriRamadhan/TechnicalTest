package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ProductionCompany(
    val id: Int?,
    val logoPath: @RawValue Any?,
    val name: String?,
    val originCountry: String?
) : Parcelable