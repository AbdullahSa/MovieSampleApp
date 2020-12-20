package com.abdullah.moviereviewapp.feature.presentation

import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.presentation.BaseViewModelFactory
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import javax.inject.Inject

class MainViewModelFactory : BaseViewModelFactory<MainViewModel>() {
    override fun provideViewModel() = MainViewModel()

    override fun provideUseCases() = arrayOf<BaseUseCase>()
}