package com.abdullah.moviereviewapp.feature.presentation.movielist.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.abdullah.moviereviewapp.base.data.network.ResponseObserver
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.movielist.MovieListViewModel
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem


class MovieListDataSourceFactory(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val viewModel: MovieListViewModel,
    val type: CategoryType = CategoryType.POPULAR,
    val responseObserver: ResponseObserver<MovieListResponse>
) : DataSource.Factory<Int, MovieListItem>() {

    private val liveData: MutableLiveData<PageKeyedDataSource<Int, MovieListItem>> =
        MutableLiveData()

    override fun create(): DataSource<Int, MovieListItem> {
        val dataSource =
            MovieListDataSource(getMovieListUseCase, viewModel, type, responseObserver)
        liveData.postValue(dataSource)
        return dataSource
    }
}