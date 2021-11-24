package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.DetailMovie

@Keep
data class DetailMovieResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genres")
    val genreResponses: List<GenreResponse>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanyResponses: List<ProductionCompanyResponse>?,
    @SerializedName("production_countries")
    val productionCountryResponses: List<ProductionCountryResponse>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguageResponses: List<SpokenLanguageResponse>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
){
    fun toDetailMovie() : DetailMovie{
        return DetailMovie(
            adult,
            backdropPath,
            belongsToCollection,
            budget,
            genreResponses?.map { it.toGenreResponse() },
            homepage,
            id,
            imdbId,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            productionCompanyResponses?.map { it.toProductionCompany() },
            productionCountryResponses?.map { it.toProductionCountry() },
            releaseDate,
            revenue,
            runtime,
            spokenLanguageResponses?.map { it.toSpokenLanguage() },
            status,
            tagline,
            title,
            video,
            voteAverage,
            voteCount
        )
    }
}