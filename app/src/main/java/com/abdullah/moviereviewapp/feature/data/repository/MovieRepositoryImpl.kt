package com.abdullah.moviereviewapp.feature.data.repository

import com.abdullah.moviereviewapp.feature.data.remotesource.MovieRemoteDataSource
import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val remoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovieList(request: MovieListRequest): Response<MovieListResponse> =
        remoteDataSource.getMovieListResponse(request)

    override suspend fun getMovieDetail(request: MovieDetailRequest) =
        remoteDataSource.getMovieDetailResponse(request)
}