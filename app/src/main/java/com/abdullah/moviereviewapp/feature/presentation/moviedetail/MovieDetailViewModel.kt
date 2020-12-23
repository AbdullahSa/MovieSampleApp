package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.abdullah.moviereviewapp.BuildConfig
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel
import com.abdullah.moviereviewapp.feature.data.response.Movie
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieDetailUseCase
import com.abdullah.moviereviewapp.feature.utils.Constants
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    BaseViewModel() {
    val observableImageUrl = ObservableField<String>()
    val observableTitle = ObservableField<String>()
    val observableDescription = ObservableField<String>()
    val observableVoteAverage = ObservableField<String>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.execute(
                GetMovieDetailObserver(this@MovieDetailViewModel), GetMovieDetailUseCase.Params(
                    BuildConfig.CONSUMER_KEY,
                    movieId
                )
            )
        }
    }

    fun handleResponse(viewEntity: MovieDetailResponse.ViewEntity) = viewEntity.run {
        observableImageUrl.set("${Constants.BASE_IMAGE_URL}${posterPath}")
        observableTitle.set(originalTitle)
        observableDescription.set(overView)
        observableVoteAverage.set(voteAverage.toString())
    }
}