package com.abdullah.moviereviewapp.base.presentation.list.model

interface BaseListItem<T> {
    fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}