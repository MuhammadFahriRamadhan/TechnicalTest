package com.example.technicaltest.core.data.response

import androidx.annotation.Keep
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.model.ReviewResult
import com.google.gson.annotations.SerializedName
@Keep
data class ReviewResponse (
    @SerializedName("id")
    val id :Long = -1L,
    @SerializedName("page")
    val page : Int = 0,
    @SerializedName("results")
    val results : List<ReviewResultResponse>
){
    fun toReview() : Review{
        return Review(
            id,
            page,
            results.map { it.toReviewResult() }
        )
    }
}

@Keep
data class  ReviewResultResponse(
    @SerializedName("author")
    val author :String? =null,
    @SerializedName("content")
    val content : String? = null
){
    fun toReviewResult(): ReviewResult {
        return ReviewResult(
            author,
            content
        )
    }
}

