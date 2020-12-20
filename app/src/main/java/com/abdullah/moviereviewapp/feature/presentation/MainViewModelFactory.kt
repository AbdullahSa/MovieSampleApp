package com.abdullah.moviereviewapp.feature.presentation

import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.presentation.BaseViewModelFactory

class MainViewModelFactory : BaseViewModelFactory<MainViewModel>() {
    override fun provideViewModel() = MainViewModel()

    override fun provideUseCases() = arrayOf<BaseUseCase>()
}