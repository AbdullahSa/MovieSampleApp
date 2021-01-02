package com.abdullah.moviereviewapp.feature.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.abdullah.moviereviewapp.base.navigation.BaseRouteDestination
import com.abdullah.moviereviewapp.base.navigation.BaseRouteNavHost
import com.abdullah.moviereviewapp.base.navigation.Event
import com.abdullah.moviereviewapp.base.presentation.BaseViewModel
import io.mockk.MockKAnnotations
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito

@RunWith(JUnit4::class)
open class BaseViewModelTest {

    private lateinit var baseViewModel: BaseViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        baseViewModel = BaseViewModel()
    }

    @Test
    fun `given mockNavigationLiveData, when navigateTo called for back, then verify updating of live data`() {
        // Given
        val mockNavigationLiveData = spyk<Observer<Event<NavController.() -> Any>>>()
        baseViewModel.navigationLiveData.observeForever(mockNavigationLiveData)

        // When
        baseViewModel.navigateTo(BaseRouteDestination.Back, bundleOf(), false)

        // Then
        verify { mockNavigationLiveData.onChanged(capture(slot())) }
    }

    @Test
    fun `given mockNavigationLiveData, when navigateTo called for nav, then verify updating of live data`() {
        // Given
        val mockNavigationLiveData = spyk<Observer<Event<NavController.() -> Any>>>()
        baseViewModel.navigationLiveData.observeForever(mockNavigationLiveData)

        // When
        baseViewModel.navigateTo(BaseRouteDestination(anyInt()), bundleOf(), false)

        // Then
        verify { mockNavigationLiveData.onChanged(capture(slot())) }
    }

    @Test
    fun `given mockNavigationLiveData, when navigateTo called for navHost, then verify updating of live data`() {
        // Given
        val mockNavigationLiveData = spyk<Observer<Event<NavController.() -> Any>>>()
        baseViewModel.navigationLiveData.observeForever(mockNavigationLiveData)

        // When
        baseViewModel.navigateTo(BaseRouteNavHost(anyInt()), bundleOf())

        // Then
        verify { mockNavigationLiveData.onChanged(capture(slot())) }
    }

    @Test
    fun `given mockDialogBoxLiveData, when openDialogBoxModel called, then verify updating of live data`() {
        // Given
        val mockDialogBoxLiveData = spyk<Observer<DialogBoxModel>>()
        baseViewModel.dialogBoxLiveData.observeForever(mockDialogBoxLiveData)

        // When
        baseViewModel.openDialogBoxModel(DialogBoxModel(anyString(), anyString(), anyString()))

        // Then
        verify { mockDialogBoxLiveData.onChanged(capture(slot())) }
    }


    @Test
    fun `given mockLoadingLiveData, when startOrPauseLoading called, then verify updating of live data`() {
        // Given
        val mockLoadingLiveData = spyk<Observer<Boolean>>()
        baseViewModel.loadingLiveData.observeForever(mockLoadingLiveData)

        // When
        baseViewModel.startOrPauseLoading(anyBoolean())

        // Then
        verify { mockLoadingLiveData.onChanged(capture(slot())) }
    }

    fun mockLifecycleOwner(): LifecycleOwner {
        val owner: LifecycleOwner = Mockito.mock(LifecycleOwner::class.java)
        val lifecycle = LifecycleRegistry(owner)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        Mockito.`when`(owner.lifecycle).thenReturn(lifecycle)
        return owner
    }
}