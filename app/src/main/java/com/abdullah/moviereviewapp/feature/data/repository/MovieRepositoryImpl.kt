package com.abdullah.moviereviewapp.feature.data.repository

import com.abdullah.moviereviewapp.base.data.model.BaseRequest
import com.abdullah.moviereviewapp.feature.data.remotesource.MovieRemoteDataSource
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val remoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovieList(baseRequest: BaseRequest): Response<MovieListResponse> =
        remoteDataSource.getMovieListResponse(baseRequest)
}