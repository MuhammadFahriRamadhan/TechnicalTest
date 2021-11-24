package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review (
    val id :Long = -1L,
    val page : Int = 0,
    val results : List<ReviewResult>
) : Parcelable

@Parcelize
data class  ReviewResult(
    val author :String? =null,
    val content : String? = null
) : Parcelable