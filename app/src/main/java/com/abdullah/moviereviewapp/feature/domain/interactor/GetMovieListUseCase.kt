package com.abdullah.moviereviewapp.feature.domain.interactor

import com.abdullah.moviereviewapp.base.domain.BaseRequestUseCase
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.MovieRepository

class GetMovieListUseCase constructor(private val repository: MovieRepository) :
    BaseRequestUseCase<MovieListResponse, GetMovieListUseCase.Params>() {

    override suspend fun networkCall(params: Params) = params.run {
        repository.getMovieList(MovieListRequest(type, apiKey, page))
    }

    class Params(
        val apiKey: String,
        val page: Int,
        val type: CategoryType
    )
}