package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.ProductionCountry

@Keep
data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?
){
    fun toProductionCountry() : ProductionCountry{
        return ProductionCountry(
            iso31661,
            name
        )
    }
}