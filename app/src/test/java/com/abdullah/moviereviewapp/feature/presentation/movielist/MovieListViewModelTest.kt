package com.abdullah.moviereviewapp.feature.presentation.movielist

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.abdullah.moviereviewapp.CoroutineTestRule
import com.abdullah.moviereviewapp.feature.data.enum.CategoryType
import com.abdullah.moviereviewapp.feature.data.response.Movie
import com.abdullah.moviereviewapp.feature.data.response.MovieListResponse
import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import com.abdullah.moviereviewapp.feature.presentation.BaseViewModelTest
import com.abdullah.moviereviewapp.feature.presentation.movielist.list.model.MovieListItem
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class MovieListViewModelTest : BaseViewModelTest() {

    companion object {
        const val TEST_STRING = "test"
        const val TEST_INT = 1
    }

    @MockK
    lateinit var getMovieListUseCase: GetMovieListUseCase

    private lateinit var movieListViewModel: MovieListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        movieListViewModel = MovieListViewModel(getMovieListUseCase)
    }

    @Test
    fun `given mockPagedListLiveData, when loadItems called, then check loaded list`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val response = Response.success(
                MovieListResponse(
                    TEST_INT, listOf(
                        Movie(
                            TEST_INT,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_INT.toFloat(),
                            TEST_INT
                        )
                    ), TEST_INT
                )
            )
            coEvery { getMovieListUseCase.executeWithPaging(any()) } returns response
            val owner = mockLifecycleOwner()
            val mockPagedListLiveData = spyk<Observer<PagedList<MovieListItem>>>()
            movieListViewModel.pagedListLiveData.observeForever(mockPagedListLiveData)

            // When
            movieListViewModel.loadItems(owner)

            // Then
            verify {
                mockPagedListLiveData.onChanged(capture(slot()))
            }
            assert(movieListViewModel.pagedListLiveData.value != null)
            assert(movieListViewModel.pagedListLiveData.value?.size == 1)
        }
    }

    @Test
    fun `given mockPagedListLiveData, when loadItemsByCategory called, then check loaded list by category`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val response = Response.success(
                MovieListResponse(
                    TEST_INT, listOf(
                        Movie(
                            TEST_INT,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_STRING,
                            TEST_INT.toFloat(),
                            TEST_INT
                        )
                    ), TEST_INT
                )
            )
            coEvery { getMovieListUseCase.executeWithPaging(any()) } returns response
            val mockPagedListLiveData = spyk<Observer<PagedList<MovieListItem>>>()
            movieListViewModel.pagedListLiveData.observeForever(mockPagedListLiveData)

            // When
            val owner = mockLifecycleOwner()
            movieListViewModel.loadItemsByCategory(owner, CategoryType.TOP_RATED)

            // Then
            verify {
                mockPagedListLiveData.onChanged(capture(slot()))
            }
            assert(movieListViewModel.pagedListLiveData.value != null)
            assert(movieListViewModel.pagedListLiveData.value?.size == 1)
        }

    }
}
