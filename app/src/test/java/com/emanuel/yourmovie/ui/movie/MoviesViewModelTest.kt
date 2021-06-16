package com.emanuel.yourmovie.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.emanuel.yourmovie.R
import com.emanuel.yourmovie.data.MoviesResult
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.repository.MoviesRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MoviesViewModel

    @Mock
    private lateinit var movieLiveDataObserver: Observer<Movie>

    @Mock
    private lateinit var viewFlipperMovieLiveDataObserver: Observer<Pair<Int, Int?>>

    @Test
    fun `when view model getMovieDetails get SUCCESS then sets movieLiveData`() {
        //Arrange
        val movie = Movie(1, "Avengers: Endgame", 12345, 205.236f, "")

        val success = MockRepository(MoviesResult.SuccessMovieDetails(movie))
        movieViewModel = MoviesViewModel(success)
        movieViewModel.movieLiveData.observeForever(movieLiveDataObserver)
        movieViewModel.viewFlipperMovieLiveData.observeForever(viewFlipperMovieLiveDataObserver)

        //Act
        movieViewModel.getMovieDetails()

        //Assert
        verify(movieLiveDataObserver).onChanged(movie)
        verify(viewFlipperMovieLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getMovieDetails get API ERROR then sets viewFlipperMovieLiveData`() {
        //Arrange
        val statusCode = 404
        val apiError = MockRepository(MoviesResult.ApiErrorMovie(statusCode))
        movieViewModel = MoviesViewModel(apiError)
        movieViewModel.viewFlipperMovieLiveData.observeForever(viewFlipperMovieLiveDataObserver)

        //Act
        movieViewModel.getMovieDetails()

        //Assert
        if (statusCode == 401) {
            verify(viewFlipperMovieLiveDataObserver).onChanged(Pair(2, R.string.error_401))
        } else {
            verify(viewFlipperMovieLiveDataObserver).onChanged(Pair(2, R.string.error_404))
        }
    }

    @Test
    fun `when view model getMovieDetails get SERVER ERROR then sets viewFlipperMovieLiveData`() {
        //Arrange
        val serverError = MockRepository(MoviesResult.ServerError)
        movieViewModel = MoviesViewModel(serverError)
        movieViewModel.viewFlipperMovieLiveData.observeForever(viewFlipperMovieLiveDataObserver)

        //Act
        movieViewModel.getMovieDetails()

        //Assert
        verify(viewFlipperMovieLiveDataObserver).onChanged(Pair(2, R.string.error_failure))
    }

}

class MockRepository(private val result: MoviesResult) : MoviesRepository {
    override fun getMovieDetails(movieResultCallback: (result: MoviesResult) -> Unit) {
        movieResultCallback(result)
    }

    override fun getSimilarMovies(similarMovieResultCallback: (result: MoviesResult) -> Unit) {
        similarMovieResultCallback(result)
    }

    override fun getGenre(genreMovieResultCallback: (result: MoviesResult) -> Unit) {
        genreMovieResultCallback(result)
    }
}