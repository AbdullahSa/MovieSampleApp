package com.abdullah.moviereviewapp.feature.presentation.movielist.list

import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.list.BasePagedListRecyclerViewAdapter
import com.abdullah.moviereviewapp.databinding.ItemViewMovieBinding
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem

class MovieListAdapter :
    BasePagedListRecyclerViewAdapter<MovieListItem, ItemViewMovieBinding>(R.layout.item_view_movie) {

    override fun bind(binding: ItemViewMovieBinding, item: MovieListItem) {
        binding.data = item
    }
}