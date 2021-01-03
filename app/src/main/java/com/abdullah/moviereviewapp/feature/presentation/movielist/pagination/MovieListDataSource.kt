package com.abdullah.moviereviewapp.feature.presentation.movielist.pagination

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.abdullah.moviereviewapp.BuildConfig
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.data.model.DialogButton
import com.abdullah.moviereviewapp.base.data.network.ResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.Movie
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.movielist.MovieListViewModel
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem
import com.abdullah.moviereviewapp.feature.utils.Constants
import com.abdullah.moviereviewapp.feature.utils.Constants.BASE_IMAGE_URL
import com.abdullah.moviereviewapp.feature.utils.Constants.FIRST_PAGE
import kotlinx.coroutines.launch

class MovieListDataSource(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val viewModel: MovieListViewModel,
    private val type: CategoryType = CategoryType.POPULAR,
    private val responseObserver: ResponseObserver<MovieListResponse>
) : PageKeyedDataSource<Int, MovieListItem>() {

    @Suppress("UNREACHABLE_CODE")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieListItem>
    ) {
        viewModel.viewModelScope.launch {
            responseObserver.loading(Status.Loading)
            val response = getMovieListUseCase.executeWithPaging(
                GetMovieListUseCase.Params(
                    BuildConfig.CONSUMER_KEY,
                    FIRST_PAGE, type
                )
            )
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    responseObserver.success(Status.Success(it, it.dialogBoxes?.get(0)))
                }
                callback.onResult(
                    (body?.toViewEntity()?.results as MutableList<Movie.ViewEntity>).map {
                        it.run {
                            MovieListItem(
                                "${BASE_IMAGE_URL}${posterPath}",
                                originalTitle.toString(),
                                releaseDate.toString(),
                                id as Int
                            )
                        }
                    },
                    null,
                    FIRST_PAGE + 1
                )
            } else {
                responseObserver.error(
                    Status.Error(
                        DialogBoxModel(
                            DialogBoxModel.TYPE_ERROR,
                            response.code().toString(),
                            response.message(),
                            dialogButton = DialogButton(Constants.OK)
                        )
                    )
                )
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieListItem>) {
        viewModel.viewModelScope.launch {
            val response = getMovieListUseCase.executeWithPaging(
                GetMovieListUseCase.Params(
                    BuildConfig.CONSUMER_KEY,
                    params.key, type
                )
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                val key =
                    responseBody?.let { if (params.key <= it.totalPages) params.key + 1 else null }
                callback.onResult(
                    (response.body()
                        ?.toViewEntity()?.results as MutableList<Movie.ViewEntity>).map {
                        it.run {
                            MovieListItem(
                                "${BASE_IMAGE_URL}${posterPath}",
                                originalTitle.toString(),
                                releaseDate.toString(),
                                id as Int
                            )
                        }
                    },
                    key
                )
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieListItem>
    ) {
    }
}