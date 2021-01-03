package com.abdullah.moviereviewapp.base.data.network

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel

abstract class ResponseObserver<T : BaseResponse>(private val baseViewModel: BaseViewModel) {
    abstract fun onSuccess(statusData: Status.Success<T>)
    abstract fun onError(statusData: Status.Error)

    fun success(status: Status.Success<T>) {
        hideLoading()
        if (status.dialogBox == null) {
            onSuccess(status)
        } else {
            handleDialogBoxes(status.dialogBox)
        }
    }

    fun error(statusData: Status.Error) {
        hideLoading()
        if (statusData.dialogBox == null) {
            onError(statusData)
        } else {
            handleDialogBoxes(statusData.dialogBox)
        }
    }

    fun loading(loading: Status.Loading) {
        showLoading()
    }

    private fun handleDialogBoxes(dialogBox: DialogBoxModel?) {
        dialogBox?.let {
            baseViewModel.openDialogBoxModel(it)
        }
    }

    private fun showLoading() {
        baseViewModel.startOrPauseLoading(true)
    }

    private fun hideLoading() {
        baseViewModel.startOrPauseLoading(false)
    }
}