package com.abdullah.moviereviewapp.feature.utils.extensions

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.abdullah.moviereviewapp.R
import com.bumptech.glide.Glide

@BindingAdapter("app:srcUrl")
fun ImageView.setUrl(url: String) {
    Glide.with(this).load(Uri.parse(url))
        .placeholder(R.drawable.placeholder_movie)
        .into(this)
}