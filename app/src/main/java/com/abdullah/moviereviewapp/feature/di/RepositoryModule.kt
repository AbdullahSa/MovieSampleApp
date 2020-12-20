package com.abdullah.moviereviewapp.feature.di

import android.content.Context
import com.abdullah.moviereviewapp.feature.data.MovieService
import com.abdullah.moviereviewapp.feature.data.remotesource.MovieRemoteDataSource
import com.abdullah.moviereviewapp.feature.data.repository.MovieRepositoryImpl
import com.abdullah.moviereviewapp.feature.domain.MovieRepository
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.MainViewModelFactory
import com.abdullah.moviereviewapp.feature.presentation.movielist.MovieListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(movieService: MovieService): MovieRemoteDataSource =
        MovieRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun provideMainViewModelFactory(): MainViewModelFactory =
        MainViewModelFactory()

    @Singleton
    @Provides
    fun provideUseCase(repository: MovieRepository): GetMovieListUseCase =
        GetMovieListUseCase(repository)

    @Singleton
    @Provides
    fun provideMovieListViewModelFactory(getMovieListUseCase: GetMovieListUseCase): MovieListViewModelFactory =
        MovieListViewModelFactory(getMovieListUseCase)
}