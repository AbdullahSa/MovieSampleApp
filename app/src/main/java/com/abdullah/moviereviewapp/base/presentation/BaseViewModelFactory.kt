package com.abdullah.moviereviewapp.base.presentation

import androidx.lifecycle.ViewModelProvider
import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import java.lang.IllegalArgumentException

abstract class BaseViewModelFactory<ViewModel : BaseViewModel> : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = provideViewModel()

        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            viewModel.addUseCases(provideUseCases())
            return viewModel as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
    }

    abstract fun provideViewModel(): ViewModel

    abstract fun provideUseCases(): Array<BaseUseCase>
}