package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.Genre

@Keep
data class GenreResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
){
    fun toGenreResponse() : Genre {
        return Genre(id,name)
    }
}