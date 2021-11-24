package com.example.technicaltest.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.technicaltest.core.data.response.GenreResponse
import com.example.technicaltest.core.data.response.ProductionCompanyResponse
import com.example.technicaltest.core.data.response.ProductionCountryResponse
import com.example.technicaltest.core.data.response.SpokenLanguageResponse
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DetailMovie(
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: @RawValue Any?,
    val budget: Int?,
    val genreResponses: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanyResponses: List<ProductionCompany>?,
    val productionCountryResponses: List<ProductionCountry>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguageResponses: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
) : Parcelable