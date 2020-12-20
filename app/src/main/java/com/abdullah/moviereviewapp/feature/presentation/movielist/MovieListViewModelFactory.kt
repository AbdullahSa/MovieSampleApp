package com.abdullah.moviereviewapp.feature.presentation.movielist

import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.presentation.BaseViewModelFactory
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import javax.inject.Inject

class MovieListViewModelFactory @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    BaseViewModelFactory<MovieListViewModel>() {

    override fun provideViewModel() = MovieListViewModel(getMovieListUseCase)

    override fun provideUseCases() = arrayOf<BaseUseCase>(getMovieListUseCase)
}