package com.abdullah.moviereviewapp.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abdullah.moviereviewapp.BuildConfig
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : BaseViewModel()