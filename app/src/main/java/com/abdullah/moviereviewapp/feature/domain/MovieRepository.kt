package com.abdullah.moviereviewapp.feature.domain

import com.abdullah.moviereviewapp.base.data.model.BaseRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieList(baseRequest: BaseRequest): Response<MovieListResponse>
}