package com.abdullah.moviereviewapp.feature.presentation.movielist

import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.presentation.BaseViewModelFactory
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase

class MovieListViewModelFactory constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    BaseViewModelFactory<MovieListViewModel>() {

    override fun provideViewModel() = MovieListViewModel(getMovieListUseCase)

    override fun provideUseCases() = arrayOf<BaseUseCase>(getMovieListUseCase)
}