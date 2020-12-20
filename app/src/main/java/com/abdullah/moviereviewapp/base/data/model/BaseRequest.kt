package com.abdullah.moviereviewapp.base.data.model

import com.abdullah.moviereviewapp.feature.data.enum.CategoryType

class BaseRequest(
    val apiKey: String,
    val page: Int,
    val type: CategoryType
)