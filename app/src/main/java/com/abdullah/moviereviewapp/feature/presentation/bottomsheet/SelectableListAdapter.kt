package com.abdullah.moviereviewapp.feature.presentation.bottomsheet

import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.list.BaseRecyclerViewAdapter
import com.abdullah.moviereviewapp.databinding.ItemSelectableViewBinding

class SelectableListAdapter(onItemClicked: ((SelectableListItem) -> Unit)) :
    BaseRecyclerViewAdapter<SelectableListItem, ItemSelectableViewBinding>(
        R.layout.item_selectable_view,
        onItemClicked
    ) {

    override fun bind(binding: ItemSelectableViewBinding, item: SelectableListItem) {
        binding.data = item
    }
}