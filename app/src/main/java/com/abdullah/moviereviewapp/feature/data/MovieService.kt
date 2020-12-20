package com.abdullah.moviereviewapp.feature.data

import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("movie/now_playing")
    suspend fun getTheLatestMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>
}