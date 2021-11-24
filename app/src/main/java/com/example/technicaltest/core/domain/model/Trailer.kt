package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailer(
    val id: Int?,
    val resultTrailerResponses: List<ResultTrailer>?
) : Parcelable