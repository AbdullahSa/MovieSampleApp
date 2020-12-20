package com.abdullah.moviereviewapp.base.presentation

import androidx.lifecycle.ViewModelProvider
import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import java.lang.IllegalArgumentException

abstract class BaseViewModelFactory<ViewModel : BaseViewModel> : ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = provideViewModel()

        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            viewModel.addUseCases(provideUseCases())
            return viewModel as T
        }

        return throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
    }

    abstract fun provideViewModel(): ViewModel

    abstract fun provideUseCases(): Array<BaseUseCase>
}