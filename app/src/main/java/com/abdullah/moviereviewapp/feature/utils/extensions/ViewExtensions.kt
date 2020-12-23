package com.abdullah.moviereviewapp.feature.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.abdullah.moviereviewapp.R
import com.bumptech.glide.Glide

@BindingAdapter("app:srcUrl")
fun AppCompatImageView.setUrl(url: String?) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.placeholder_movie)
        .into(this)
}