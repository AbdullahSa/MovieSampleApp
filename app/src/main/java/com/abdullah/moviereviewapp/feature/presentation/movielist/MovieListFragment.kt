package com.abdullah.moviereviewapp.feature.presentation.movielist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.BaseFragment
import com.abdullah.moviereviewapp.databinding.FragmentMovieListBinding
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.presentation.bottomsheet.SelectableBottomSheetDialog
import com.abdullah.moviereviewapp.feature.presentation.bottomsheet.SelectableListItem
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListViewModel, FragmentMovieListBinding>() {

    @Inject
    lateinit var movieListViewModelFactory: MovieListViewModelFactory

    private val listAdapter by lazy {
        MovieListAdapter()
    }

    override fun provideLayoutResId() = R.layout.fragment_movie_list

    override fun provideViewModel() =
        ViewModelProvider(this, movieListViewModelFactory).get(MovieListViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding().rvMovie.layoutManager = GridLayoutManager(context, 3)
        getBinding().rvMovie.setHasFixedSize(true)
        getBinding().rvMovie.adapter = listAdapter
        getBinding().clCategory.setOnClickListener {
            SelectableBottomSheetDialog.newInstance(
                arrayListOf(
                    CategoryType.POPULAR.let { SelectableListItem(it, it.text) },
                    CategoryType.UP_COMING.let { SelectableListItem(it, it.text) },
                    CategoryType.NOW_PLAYING.let { SelectableListItem(it, it.text) },
                    CategoryType.TOP_RATED.let { SelectableListItem(it, it.text) }
                )
            ) {
                getBinding().tvDrawer.text = it.title
                getViewModel().getMovieList(it.id)
            }.show(childFragmentManager, "TAG")
        }
        getViewModel().getMovieList()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        getViewModel().listLiveData.observe(this, Observer {
            getBinding().clCategory.visibility = View.VISIBLE
            listAdapter.submitList(it)
        })
    }

    override fun bindViewModel(dataBinding: FragmentMovieListBinding) {
        dataBinding.viewModel = getViewModel()
    }

}