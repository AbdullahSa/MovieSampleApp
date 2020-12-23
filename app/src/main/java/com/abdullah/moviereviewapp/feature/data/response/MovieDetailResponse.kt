package com.abdullah.moviereviewapp.feature.data.response

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int
) : BaseResponse() {

    data class ViewEntity(
        val id: Int?,
        val posterPath: String?,
        val title: String?,
        val originalTitle: String?,
        val overView: String?,
        val releaseDate: String?,
        val voteAverage: Float?,
        val voteCount: Int?
    )

    fun toViewEntity() = ViewEntity(
        id,
        posterPath,
        title,
        originalTitle,
        overView,
        releaseDate,
        voteAverage,
        voteCount
    )

}