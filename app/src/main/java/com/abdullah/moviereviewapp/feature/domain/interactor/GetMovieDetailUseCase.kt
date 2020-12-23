package com.abdullah.moviereviewapp.feature.domain.interactor

import com.abdullah.moviereviewapp.base.domain.BaseRequestUseCase
import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse
import com.abdullah.moviereviewapp.feature.domain.MovieRepository

class GetMovieDetailUseCase constructor(private val repository: MovieRepository) :
    BaseRequestUseCase<MovieDetailResponse, GetMovieDetailUseCase.Params>() {

    override suspend fun networkCall(params: Params) = params.run {
        repository.getMovieDetail(MovieDetailRequest(movieId, apiKey))
    }

    class Params(
        val apiKey: String,
        val movieId: Int
    )
}