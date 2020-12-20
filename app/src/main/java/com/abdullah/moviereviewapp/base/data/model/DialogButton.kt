package com.abdullah.moviereviewapp.base.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogButton(
    val text: String?
) : Parcelable