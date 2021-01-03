package com.abdullah.moviereviewapp.base.data.network

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel

sealed class Status {
    data class Success<T : BaseResponse>(val data: T, val dialogBox: DialogBoxModel? = null) : Status()
    data class Error(val dialogBox: DialogBoxModel? = null) : Status()
    object Loading
}