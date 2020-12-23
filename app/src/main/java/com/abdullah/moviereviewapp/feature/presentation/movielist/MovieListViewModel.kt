package com.abdullah.moviereviewapp.feature.presentation.movielist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem
import com.abdullah.moviereviewapp.feature.presentation.movielist.pagination.MovieListDataSourceFactory
import com.abdullah.moviereviewapp.feature.utils.Constants.ITEM_SIZE


class MovieListViewModel constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    BaseViewModel(), Observer<PagedList<MovieListItem>> {

    private val listMutableLiveData = MutableLiveData<PagedList<MovieListItem>>()
    val listLiveData: LiveData<PagedList<MovieListItem>> get() = listMutableLiveData

    private val observer = MovieListObserver(this)

    var pagedListLiveData: LiveData<PagedList<MovieListItem>>
    private var sourceFactory = MovieListDataSourceFactory(
        getMovieListUseCase,
        this,
        baseResponseObserver = observer
    )

    private val config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ITEM_SIZE)
            .build()
    }

    init {
        pagedListLiveData = LivePagedListBuilder(
            sourceFactory, config
        ).build()
    }

    fun loadItems(viewLifecycleOwner: LifecycleOwner) {
        pagedListLiveData.observe(viewLifecycleOwner, this)
    }

    fun loadItemsByCategory(viewLifecycleOwner: LifecycleOwner, type: CategoryType) {
        replaceSubscription(viewLifecycleOwner, type)
        loadItems(viewLifecycleOwner)
    }

    private fun replaceSubscription(
        lifecycleOwner: LifecycleOwner?,
        type: CategoryType? = null
    ) {
        if (lifecycleOwner != null) {
            pagedListLiveData.removeObservers(lifecycleOwner)
        }
        sourceFactory = if (type == null) {
            MovieListDataSourceFactory(getMovieListUseCase, this, baseResponseObserver = observer)
        } else {
            MovieListDataSourceFactory(
                getMovieListUseCase,
                this,
                type,
                baseResponseObserver = observer
            )
        }
        pagedListLiveData = LivePagedListBuilder<Int, MovieListItem>(sourceFactory, config).build()
    }

    override fun onChanged(pagedList: PagedList<MovieListItem>?) {
        this.listMutableLiveData.value = pagedList
    }

}