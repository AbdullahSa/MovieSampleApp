package com.abdullah.moviereviewapp.feature.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdullah.moviereviewapp.CoroutineTestRule
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
open class GetMovieDetailUseCaseTest {

    @MockK
    lateinit var repository: MovieRepository

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMovieDetailUseCase = GetMovieDetailUseCase(repository)
    }

    @Test
    fun `given params, when networkCall called, then verify getMovieDetail from repository`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val params = GetMovieDetailUseCase.Params(
                anyString(),
                anyInt()
            )
            coEvery { repository.getMovieDetail(any()) } returns mockk()

            // When
            getMovieDetailUseCase.networkCall(params)

            // Then
            coVerify {
                repository.getMovieDetail(any())
            }
        }
    }
}