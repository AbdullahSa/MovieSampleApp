package com.abdullah.moviereviewapp.base.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.abdullah.moviereviewapp.base.presentation.list.model.BaseListItem

class BaseRecyclerItemCallback<T : BaseListItem<T>> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.areContentsTheSame(oldItem, newItem)
}