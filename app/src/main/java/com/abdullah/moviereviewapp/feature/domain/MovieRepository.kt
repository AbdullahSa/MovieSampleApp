package com.abdullah.moviereviewapp.feature.domain

import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieList(request: MovieListRequest): Response<MovieListResponse>

    suspend fun getMovieDetail(request: MovieDetailRequest): Response<MovieDetailResponse>
}