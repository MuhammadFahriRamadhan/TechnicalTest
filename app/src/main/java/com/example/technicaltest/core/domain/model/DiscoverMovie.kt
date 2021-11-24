package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DiscoverMovie (
    val page: Int?,
    val resultDataRespons: List<ResultData>?,
    val totalPages: Int?,
    val totalResults: Int?
) : Parcelable