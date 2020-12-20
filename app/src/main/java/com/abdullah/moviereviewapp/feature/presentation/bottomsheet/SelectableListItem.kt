package com.abdullah.moviereviewapp.feature.presentation.bottomsheet

import com.abdullah.moviereviewapp.base.presentation.list.model.BaseListItem
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType

data class SelectableListItem(
    val id: CategoryType,
    val title: String
) : BaseListItem<SelectableListItem> {
    override fun areItemsTheSame(
        oldItem: SelectableListItem,
        newItem: SelectableListItem
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: SelectableListItem,
        newItem: SelectableListItem
    ) = oldItem.id == newItem.id
            && oldItem.title == newItem.title
}