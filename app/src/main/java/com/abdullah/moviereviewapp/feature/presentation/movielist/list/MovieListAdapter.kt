package com.abdullah.moviereviewapp.feature.presentation.movielist.list

import android.net.Uri
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.list.BaseRecyclerViewAdapter
import com.abdullah.moviereviewapp.databinding.ItemViewMovieBinding
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem
import com.bumptech.glide.Glide

class MovieListAdapter :
    BaseRecyclerViewAdapter<MovieListItem, ItemViewMovieBinding>(R.layout.item_view_movie) {

    override fun bind(binding: ItemViewMovieBinding, item: MovieListItem) {
        binding.data = item
    }
}