package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.BaseFragment
import com.abdullah.moviereviewapp.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    private var movieId: Int? = null

    @Inject
    lateinit var movieDetailViewModelFactory: MovieDetailViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras()
        getBinding().ivBack.setOnClickListener {
            onBackPressed()
        }
        movieId?.let { getViewModel().getMovieDetail(it) }
    }

    fun getExtras() {
        arguments?.get(EXTRA_MOVIE_ID)?.let {
            movieId = it as Int
        }
    }

    override fun provideLayoutResId() = R.layout.fragment_movie_detail

    override fun provideViewModel() =
        ViewModelProvider(this, movieDetailViewModelFactory).get(MovieDetailViewModel::class.java)

    override fun bindViewModel(dataBinding: FragmentMovieDetailBinding) {
        dataBinding.viewModel = getViewModel()
    }
}