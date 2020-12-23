package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.presentation.BaseViewModelFactory
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieDetailUseCase

class MovieDetailViewModelFactory constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    BaseViewModelFactory<MovieDetailViewModel>() {
    override fun provideViewModel() = MovieDetailViewModel(getMovieDetailUseCase)
    override fun provideUseCases(): Array<BaseUseCase> = arrayOf(getMovieDetailUseCase)
}