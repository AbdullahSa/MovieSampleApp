package com.abdullah.moviereviewapp.feature.presentation.movielist

import com.abdullah.moviereviewapp.base.data.network.BaseResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse

class GetMovieListObserver(private val viewModel: MovieListViewModel) :
    BaseResponseObserver<MovieListResponse>(viewModel) {

    override fun onSuccess(statusData: Status<MovieListResponse>) {
        statusData.data?.let { viewModel.handleSuccess(it) }
    }

    override fun onError(statusData: Status<MovieListResponse>) {
    }
}