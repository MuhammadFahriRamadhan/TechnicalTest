package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.SpokenLanguage

@Keep
data class SpokenLanguageResponse(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("name")
    val name: String?
){
    fun toSpokenLanguage() : SpokenLanguage{
        return SpokenLanguage(
            englishName,
            iso6391,
            name)
    }
}