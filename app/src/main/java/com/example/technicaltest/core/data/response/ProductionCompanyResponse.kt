package com.example.technicaltest.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.ProductionCompany

@Keep
data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
){
    fun toProductionCompany() : ProductionCompany{
        return ProductionCompany(
            id,
            logoPath,
            name,
            originCountry
        )
    }
}