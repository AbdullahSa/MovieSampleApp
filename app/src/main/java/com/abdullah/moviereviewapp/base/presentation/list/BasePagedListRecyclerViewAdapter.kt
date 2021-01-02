package com.abdullah.moviereviewapp.base.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import com.abdullah.moviereviewapp.base.presentation.list.model.BaseListItem

abstract class BasePagedListRecyclerViewAdapter<Item : BaseListItem<Item>, DB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val onItemClicked: ((Item) -> Unit)? = null
) : PagedListAdapter<Item, BaseViewHolder<DB>>(BaseRecyclerItemCallback<Item>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val binding: DB =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResId, parent, false)
        return BaseViewHolder(binding) {
            currentList?.get(it)?.let { item -> onItemClicked?.invoke(item) }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        getItem(position)?.let { bind(holder.dataBinding, it) }
        holder.bindItem()
    }

    abstract fun bind(binding: DB, item: Item)
}