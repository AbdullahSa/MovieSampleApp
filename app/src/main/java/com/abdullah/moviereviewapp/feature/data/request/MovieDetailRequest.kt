package com.abdullah.moviereviewapp.feature.data.request

import com.abdullah.moviereviewapp.base.data.model.BaseRequest

data class MovieDetailRequest(
    val movieId: Int,
    val key: String
) : BaseRequest(apiKey = key)