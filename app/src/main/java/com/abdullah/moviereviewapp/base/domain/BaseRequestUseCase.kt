package com.abdullah.moviereviewapp.base.domain

import com.abdullah.moviereviewapp.base.data.model.BaseResponse
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.data.model.DialogButton
import com.abdullah.moviereviewapp.base.data.network.ResponseObserver
import com.abdullah.moviereviewapp.base.data.network.Status
import com.abdullah.moviereviewapp.feature.utils.Constants.OK
import retrofit2.Response
import timber.log.Timber

abstract class BaseRequestUseCase<T : BaseResponse, Params> :
    BaseUseCase() {

    abstract suspend fun networkCall(params: Params): Response<T>

    suspend fun execute(
        responseObserver: ResponseObserver<T>,
        params: Params
    ) {
        try {
            responseObserver.loading(Status.Loading)
            val response = networkCall(params)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    responseObserver.success(
                        Status.Success(body, body.dialogBoxes?.get(0))
                    )
                }
            } else {
                responseObserver.error(
                    Status.Error(
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
            responseObserver.error(
                Status.Error(
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

    private fun <T : BaseResponse> error(dialogBoxModel: DialogBoxModel): Status {
        Timber.d(dialogBoxModel.message)
        return Status.Error(dialogBoxModel)
    }

}