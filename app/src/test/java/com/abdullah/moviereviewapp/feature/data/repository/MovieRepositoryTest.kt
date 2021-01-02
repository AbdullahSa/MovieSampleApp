package com.abdullah.moviereviewapp.feature.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdullah.moviereviewapp.CoroutineTestRule
import com.abdullah.moviereviewapp.feature.data.remotesource.MovieRemoteDataSource
import com.abdullah.moviereviewapp.feature.data.request.MovieDetailRequest
import com.abdullah.moviereviewapp.feature.data.request.MovieListRequest
import com.abdullah.moviereviewapp.feature.domain.MovieRepository
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

@ExperimentalCoroutinesApi
class MovieRepositoryTest {
    @MockK
    lateinit var remoteDataSource: MovieRemoteDataSource

    private lateinit var movieRepository: MovieRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieRepository = MovieRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `given params, when getMovieList called, then verify getMovieListResponse from remoteDataSource`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val mockRequest = mockk<MovieListRequest>()
            coEvery { remoteDataSource.getMovieListResponse(any()) } returns mockk()

            // When
            movieRepository.getMovieList(mockRequest)

            // Test
            coVerify {
                remoteDataSource.getMovieListResponse(any())
            }
        }
    }

    @Test
    fun `given params, when getMovieDetail called, then verify getMovieDetailResponse from remoteDataSource`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val mockRequest = mockk<MovieDetailRequest>()
            coEvery { remoteDataSource.getMovieDetailResponse(any()) } returns mockk()

            // When
            movieRepository.getMovieDetail(mockRequest)

            // Test
            coVerify {
                remoteDataSource.getMovieDetailResponse(any())
            }
        }
    }
}