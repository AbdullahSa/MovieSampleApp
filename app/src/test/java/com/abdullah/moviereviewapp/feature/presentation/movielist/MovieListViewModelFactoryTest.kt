package com.abdullah.moviereviewapp.feature.presentation.movielist

import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieListUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieListViewModelFactoryTest {

    @MockK
    lateinit var getMovieListUseCase: GetMovieListUseCase

    private lateinit var viewModelFactory: MovieListViewModelFactory

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelFactory = MovieListViewModelFactory(getMovieListUseCase)
    }

    @Test
    fun testViewModel() {
        // Given

        // When
        viewModelFactory.provideViewModel()

        // Then
        assertEquals(getMovieListUseCase, viewModelFactory.getMovieListUseCase)
    }

    @Test
    fun testUseCases() {
        // Given

        // When
        val viewModel = viewModelFactory.create(MovieListViewModel::class.java)

        // Then
        assertNotNull(viewModel)
        assertTrue(listOf(viewModel.useCases).isNotEmpty())
    }

}