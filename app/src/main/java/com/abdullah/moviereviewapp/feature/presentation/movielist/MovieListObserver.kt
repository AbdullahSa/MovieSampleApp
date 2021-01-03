package com.abdullah.moviereviewapp.feature.presentation.movielist

import com.abdullah.moviereviewapp.base.data.network.ResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse

class MovieListObserver(viewModel: MovieListViewModel) :
    ResponseObserver<MovieListResponse>(viewModel) {

    override fun onSuccess(statusData: Status.Success<MovieListResponse>) {
        // Do Nothing
    }

    override fun onError(statusData: Status.Error) {
        // Do Nothing
    }
}