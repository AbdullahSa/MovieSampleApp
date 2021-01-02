package com.abdullah.moviereviewapp.feature.presentation.moviedetail

import com.abdullah.moviereviewapp.feature.domain.interactor.GetMovieDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelFactoryTest {

    @MockK
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelFactory = MovieDetailViewModelFactory(getMovieDetailUseCase)
    }

    @Test
    fun testViewModel() {
        // Given

        // When
        viewModelFactory.provideViewModel()

        // Then
        assertEquals(getMovieDetailUseCase, viewModelFactory.getMovieDetailUseCase)
    }

    @Test
    fun testUseCases() {
        // Given

        // When
        val viewModel = viewModelFactory.create(MovieDetailViewModel::class.java)

        // Then
        assertNotNull(viewModel)
        assertTrue(listOf(viewModel.useCases).isNotEmpty())
    }

}