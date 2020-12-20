package com.abdullah.moviereviewapp.base.domain

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.data.model.DialogButton
import com.abdullah.moviereviewapp.base.data.network.BaseResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.utils.Constants.OK
import retrofit2.Response
import timber.log.Timber

abstract class BaseRequestUseCase<T : BaseResponse, Params> :
    BaseUseCase() {

    abstract suspend fun networkCall(params: Params): Response<T>

    suspend fun execute(
        baseResponseObserver: BaseResponseObserver<T>,
        params: Params
    ) {
        try {
            baseResponseObserver.handleResponse(Status.loading(null))
            val response = networkCall(params)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    baseResponseObserver.handleResponse(
                        Status.success(body, body.dialogBoxes?.get(0))
                    )
                }
            } else {
                baseResponseObserver.handleResponse(
                    error(
                        DialogBoxModel(
                            DialogBoxModel.TYPE_ERROR,
                            response.code().toString(),
                            response.message(),
                            dialogButton = DialogButton(OK)
                        )
                    )
                )
            }
        } catch (e: Exception) {
            baseResponseObserver.handleResponse(
                error(
                    DialogBoxModel(
                        null,
                        null,
                        message = e.message ?: e.toString(),
                        dialogButton = DialogButton(OK)
                    )
                )
            )
        }
    }

    suspend fun executeWithPaging(
        params: Params
    ): Response<T> {
        return networkCall(params)
    }

    private fun <T : BaseResponse> error(dialogBoxModel: DialogBoxModel): Status<T> {
        Timber.d(dialogBoxModel.message)
        return Status.error(dialogBoxModel)
    }

}