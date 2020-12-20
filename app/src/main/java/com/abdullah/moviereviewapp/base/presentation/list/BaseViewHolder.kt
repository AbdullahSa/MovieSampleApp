package com.abdullah.moviereviewapp.base.presentation.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<DB : ViewDataBinding>(
    val dataBinding: DB,
    var onItemClick: ((Int) -> Unit)? = null
) : RecyclerView.ViewHolder(dataBinding.root) {

    init {
        itemView.setOnClickListener {
            onItemClick?.invoke(adapterPosition)
        }
    }

    fun bindItem() {
        dataBinding.executePendingBindings()
    }
}