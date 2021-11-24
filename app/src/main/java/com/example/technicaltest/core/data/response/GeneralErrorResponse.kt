package com.example.technicaltest.core.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GeneralErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)