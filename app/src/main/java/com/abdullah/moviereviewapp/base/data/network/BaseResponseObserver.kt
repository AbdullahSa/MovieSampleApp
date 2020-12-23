package com.abdullah.moviereviewapp.base.data.network

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel

abstract class BaseResponseObserver<T : BaseResponse>(private val baseViewModel: BaseViewModel) {

    abstract fun onSuccess(statusData: Status<T>)

    abstract fun onError(statusData: Status<T>)

    fun handleResponse(statusData: Status<T>) {
        when (statusData.status) {
            Status.StatusType.SUCCESS -> handleSuccess(statusData)
            Status.StatusType.ERROR -> handleError(statusData)
            Status.StatusType.LOADING -> handleLoading(true)
        }
    }

    private fun handleSuccess(statusData: Status<T>) {
        handleLoading(false)
        if (statusData.dialogBox == null) {
            onSuccess(statusData)
        } else {
            handleDialogBoxes(statusData.dialogBox)
        }
    }

    private fun handleError(statusData: Status<T>) {
        handleLoading(false)
        if (statusData.dialogBox == null) {
            onError(statusData)
        } else {
            handleDialogBoxes(statusData.dialogBox)
        }
    }

    private fun handleDialogBoxes(dialogBox: DialogBoxModel?) {
        dialogBox?.let {
            baseViewModel.openDialogBoxModel(it)
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        isLoading.let {
            baseViewModel.startOrPauseLoading(it)
        }
    }

    fun getViewModel() = baseViewModel
}