package com.abdullah.moviereviewapp.feature.data.remotesource

import com.abdullah.moviereviewapp.base.data.model.BaseRequest
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.feature.data.MovieService
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) {
    suspend fun getMovieListResponse(baseRequest: BaseRequest): Response<MovieListResponse> =
        when (baseRequest.type) {
            CategoryType.POPULAR -> movieService.getPopularMovieList(
                baseRequest.apiKey,
                baseRequest.page
            )
            CategoryType.UP_COMING -> movieService.getUpcomingMovieList(
                baseRequest.apiKey,
                baseRequest.page
            )
            CategoryType.NOW_PLAYING -> movieService.getTheLatestMovieList(
                baseRequest.apiKey,
                baseRequest.page
            )
            CategoryType.TOP_RATED -> movieService.getTopRatedMovieList(
                baseRequest.apiKey,
                baseRequest.page
            )
        }
}