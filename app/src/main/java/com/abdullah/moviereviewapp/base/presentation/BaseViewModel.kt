package com.abdullah.moviereviewapp.base.presentation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.domain.BaseUseCase
import com.abdullah.moviereviewapp.base.navigation.*

open class BaseViewModel : ViewModel() {

    var useCases: Array<BaseUseCase> = arrayOf()

    private val dialogBoxMutableLiveData = MutableLiveData<DialogBoxModel>()
    val dialogBoxLiveData: LiveData<DialogBoxModel> get() = dialogBoxMutableLiveData

    private val loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = loadingMutableLiveData

    private val navigationMutableLiveData = MutableLiveData<Event<NavController.() -> Any>>()
    val navigationLiveData: LiveData<Event<NavController.() -> Any>> get() = navigationMutableLiveData

    fun addUseCases(useCases: Array<BaseUseCase>) {
        this.useCases = useCases
    }

    fun navigateTo(route: BaseRouteNavHost, args: Bundle?) {
        withNavController { navigate(route.graph, args, defaultNavOptions) }
    }

    fun navigateTo(route: BaseRouteDestination, args: Bundle?, clearStack: Boolean) {
        when {
            route is BaseRouteDestination.Back -> withNavController { popBackStack() }
            clearStack -> withNavController { popBackStack(route.destination, false) }
            else -> withNavController { navigate(route.destination, args, defaultNavOptions) }
        }
    }

    private fun withNavController(block: NavController.() -> Any) {
        navigationMutableLiveData.postValue(Event(block))
    }

    fun startOrPauseLoading(isLoading: Boolean) {
        loadingMutableLiveData.value = isLoading
    }

    fun openDialogBoxModel(dialogBoxModel: DialogBoxModel) {
        dialogBoxMutableLiveData.value = dialogBoxModel
    }

    fun onBackPressed() {}

}