package com.abdullah.moviereviewapp.feature.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abdullah.moviereviewapp.BuildConfig
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    BaseViewModel() {

    private val listMutableLiveData = MutableLiveData<List<MovieListItem>>()
    val listLiveData: LiveData<List<MovieListItem>> get() = listMutableLiveData

    fun getMovieList(type: CategoryType = CategoryType.POPULAR) {
        viewModelScope.launch {
            getMovieListUseCase.execute(
                GetMovieListObserver(this@MovieListViewModel),
                GetMovieListUseCase.Params(BuildConfig.CONSUMER_KEY, 1, type)
            )
        }
    }

    fun handleSuccess(movieListResp: MovieListResponse) {
        this.listMutableLiveData.value = movieListResp.results.map {
            MovieListItem(
                "https://image.tmdb.org/t/p/w500${it.posterPath}",
                it.originalTitle,
                it.releaseDate
            )
        }
    }

}