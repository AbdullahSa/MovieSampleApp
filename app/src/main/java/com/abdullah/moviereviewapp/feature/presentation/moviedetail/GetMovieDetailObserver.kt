package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import com.abdullah.moviereviewapp.base.data.network.ResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse

class GetMovieDetailObserver(private val viewModel: MovieDetailViewModel) :
    ResponseObserver<MovieDetailResponse>(viewModel) {

    override fun onSuccess(statusData: Status.Success<MovieDetailResponse>) {
        statusData.data.toViewEntity().let { viewModel.handleResponse(it) }
    }

    override fun onError(statusData: Status.Error) {
    }
}