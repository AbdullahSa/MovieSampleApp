package com.abdullah.moviereviewapp.feature.presentation.movielist.list.model

import com.abdullah.moviereviewapp.base.presentation.list.model.BaseListItem

data class MovieListItem(
    val imageUrl: String,
    val title: String,
    val date: String,
    val id: Int
) : BaseListItem<MovieListItem> {
    override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem) =
        oldItem.date == newItem.date
                && oldItem.imageUrl == newItem.imageUrl
                && oldItem.title == newItem.title
                && oldItem.id == newItem.id

}