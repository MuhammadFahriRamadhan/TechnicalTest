package com.example.technicaltest.core.data.response

import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.google.gson.annotations.SerializedName

@Keep
data class DiscoverMovieResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val resultResponses: List<ResultResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
){
    fun toDiscoveryMovie() : DiscoverMovie {
        return DiscoverMovie(
            page,
            resultResponses?.map { it.toResult() },
            totalPages,
            totalResults
        )
    }
}