package com.abdullah.moviereviewapp.base.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status_code")
    val errorCode: String? = null,
    @SerializedName("status_message")
    val errorMessage: String? = null,
    val dialogBoxes: List<DialogBoxModel>? = null
)