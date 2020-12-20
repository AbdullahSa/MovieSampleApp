package com.abdullah.moviereviewapp.base.data.network

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel


data class Status<T : BaseResponse>(
    val status: StatusType,
    val data: T?,
    val dialogBox: DialogBoxModel?
) {

    enum class StatusType {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T : BaseResponse> success(data: T, dialogBox: DialogBoxModel? = null): Status<T> =
            Status(StatusType.SUCCESS, data, dialogBox)

        fun <T : BaseResponse> error(dialogBox: DialogBoxModel, data: T? = null): Status<T> =
            Status(StatusType.ERROR, data, dialogBox)

        fun <T : BaseResponse> loading(data: T? = null): Status<T> =
            Status(StatusType.LOADING, data, null)
    }
}