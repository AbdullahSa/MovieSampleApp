package com.abdullah.moviereviewapp.feature.data.remotesource

import com.abdullah.moviereviewapp.feature.data.MovieService
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) {
    suspend fun getMovieListResponse(request: MovieListRequest): Response<MovieListResponse> =
        when (request.type) {
            CategoryType.POPULAR -> movieService.getPopularMovieList(
                request.apiKey,
                request.page
            )
            CategoryType.UP_COMING -> movieService.getUpcomingMovieList(
                request.apiKey,
                request.page
            )
            CategoryType.NOW_PLAYING -> movieService.getTheLatestMovieList(
                request.apiKey,
                request.page
            )
            CategoryType.TOP_RATED -> movieService.getTopRatedMovieList(
                request.apiKey,
                request.page
            )
        }


    suspend fun getMovieDetailResponse(baseRequest: MovieDetailRequest): Response<MovieDetailResponse> =
        movieService.getMovieDetail(baseRequest.movieId, baseRequest.apiKey)
}