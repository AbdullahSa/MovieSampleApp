package com.abdullah.moviereviewapp.feature.data.request

import com.abdullah.moviereviewapp.base.data.model.BaseRequest
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType

data class MovieListRequest(
    val type: CategoryType,
    val key: String,
    val pageNumber: Int
) : BaseRequest(key, pageNumber)