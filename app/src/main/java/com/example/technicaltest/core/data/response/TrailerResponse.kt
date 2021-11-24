package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.Trailer

@Keep
data class TrailerResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val resultTrailerResponses: List<ResultTrailerResponse>?
){
    fun toTrailer() : Trailer{
       return Trailer(id,resultTrailerResponses?.map { it.toResultTrailer() })
    }
}