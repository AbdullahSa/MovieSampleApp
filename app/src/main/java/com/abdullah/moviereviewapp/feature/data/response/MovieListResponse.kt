package com.abdullah.moviereviewapp.feature.data.response

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>
) : BaseResponse() {

    data class ViewEntity(
        val page: Int?,
        val results: List<Movie.ViewEntity>?
    )

    fun toViewEntity() = ViewEntity(
        page,
        results.map { it.toViewEntity() }
    )
}