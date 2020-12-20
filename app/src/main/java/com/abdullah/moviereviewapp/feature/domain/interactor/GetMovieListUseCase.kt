package com.abdullah.moviereviewapp.feature.domain.interactor

import com.abdullah.moviereviewapp.base.data.model.BaseRequest
import com.abdullah.moviereviewapp.base.domain.BaseRequestUseCase
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) :
    BaseRequestUseCase<MovieListResponse, GetMovieListUseCase.Params>() {

    override suspend fun networkCall(params: Params) = params.run {
        repository.getMovieList(BaseRequest(apiKey, page, type))
    }

    class Params(
        val apiKey: String,
        val page: Int,
        val type: CategoryType
    )
}