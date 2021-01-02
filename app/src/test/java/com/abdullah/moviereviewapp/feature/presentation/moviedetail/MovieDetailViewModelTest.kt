package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import com.abdullah.moviereviewapp.CoroutineTestRule
import com.abdullah.moviereviewapp.feature.data.response.MovieDetailResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieDetailUseCase
import com.abdullah.moviereviewapp.feature.presentation.BaseViewModelTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest : BaseViewModelTest() {

    companion object {
        const val TEST_STRING = "test"
        const val TEST_INT = 1
    }

    @MockK
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel(getMovieDetailUseCase)
    }

    @Test
    fun `given nothing, when getMovieDetail called, then verify executing getMovieDetailUseCase`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            coEvery {
                getMovieDetailUseCase.execute(
                    any<GetMovieDetailObserver>(),
                    any()
                )
            } returns Unit

            // When
            movieDetailViewModel.getMovieDetail(TEST_INT)

            // Then
            coVerify {
                getMovieDetailUseCase.execute(
                    any<GetMovieDetailObserver>(),
                    any()
                )
            }
        }
    }

    @Test
    fun `given nothing, when handleResponse called, then check empty data`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val sampleViewEntity = null

            // When
            movieDetailViewModel.handleResponse(sampleViewEntity)

            //Then
            assert(movieDetailViewModel.observableTitle.get() == null)
            assert(movieDetailViewModel.observableDescription.get() == null)
            assert(movieDetailViewModel.observableImageUrl.get() == null)
            assert(movieDetailViewModel.observableVoteAverage.get() == null)
        }
    }

        @Test
        fun `given nothing, when handleResponse called, then check data`() {
            coroutineTestRule.dispatcher.runBlockingTest {
                // Given
                val sampleViewEntity = MovieDetailResponse.ViewEntity(
                    TEST_INT,
                    TEST_STRING,
                    TEST_STRING,
                    TEST_STRING,
                    TEST_STRING,
                    TEST_STRING,
                    TEST_INT.toFloat(),
                    TEST_INT
                )

                // When
                movieDetailViewModel.handleResponse(sampleViewEntity)

                //Then
                assert(movieDetailViewModel.observableTitle.get() != null)
                assert(movieDetailViewModel.observableDescription.get() != null)
                assert(movieDetailViewModel.observableImageUrl.get() != null)
                assert(movieDetailViewModel.observableVoteAverage.get() != null)
            }
        }
    }