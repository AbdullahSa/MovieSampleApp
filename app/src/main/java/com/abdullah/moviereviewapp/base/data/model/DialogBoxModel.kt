package com.abdullah.moviereviewapp.base.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogBoxModel(
    val type: String?,
    val title: String?,
    val message: String?,
    val dialogButton: DialogButton? = null,
    val secondDialogButton: DialogButton? = null
) : Parcelable {
    companion object {
        const val TYPE_ERROR = "Error"
        const val TYPE_CONFIRMATION = "Confirmation"
        const val TYPE_INFORMATION = "Information"
    }
}