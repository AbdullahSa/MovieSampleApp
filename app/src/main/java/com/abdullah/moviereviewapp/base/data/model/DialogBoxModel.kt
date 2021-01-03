package com.abdullah.moviereviewapp.base.data.model

import android.os.Parcelable
import androidx.annotation.StringDef
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogBoxModel(
    @DialogType val type: String?,
    val title: String?,
    val message: String?,
    val dialogButton: DialogButton? = null,
    val secondDialogButton: DialogButton? = null
) : Parcelable {
    @StringDef(TYPE_ERROR, TYPE_CONFIRMATION, TYPE_INFORMATION)
    @Retention(AnnotationRetention.SOURCE)
    annotation class DialogType

    companion object {
        const val TYPE_ERROR = "Error"
        const val TYPE_CONFIRMATION = "Confirmation"
        const val TYPE_INFORMATION = "Information"
    }
}



