package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.ResultTrailer

@Keep
data class ResultTrailerResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("official")
    val official: Boolean?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("type")
    val type: String?
){
    fun toResultTrailer() : ResultTrailer {
        return ResultTrailer(
            id,
            iso31661,
            iso6391,
            key,
            name,
            official,
            publishedAt,
            site,
            size,
            type
        )
    }
}