package com.abdullah.moviereviewapp.feature.data.remotesource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdullah.moviereviewapp.CoroutineTestRule
import com.abdullah.moviereviewapp.feature.data.MovieService
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceTest {

    @MockK
    lateinit var movieService: MovieService

    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieRemoteDataSource = MovieRemoteDataSource(movieService)
    }

    @Test
    fun `given params, when getMovieList called, then verify getMovieListResponse from remoteDataSource`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val mockRequest = MovieListRequest(
                CategoryType.POPULAR,
                anyString(),
                anyInt()
            )
            coEvery { movieService.getPopularMovieList(anyString(), anyInt()) } returns mockk()

            // When
            movieRemoteDataSource.getMovieListResponse(mockRequest)

            // Test
            coVerify {
                movieService.getPopularMovieList(anyString(), anyInt())
            }
        }
    }

    @Test
    fun `given params, when getMovieDetail called, then verify getMovieDetailResponse from remoteDataSource`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val mockRequest = MovieDetailRequest(anyInt(), anyString())
            coEvery { movieService.getMovieDetail(anyInt(), anyString()) } returns mockk()

            // When
            movieRemoteDataSource.getMovieDetailResponse(mockRequest)

            // Test
            coVerify {
                movieService.getMovieDetail(anyInt(), anyString())
            }
        }
    }
}