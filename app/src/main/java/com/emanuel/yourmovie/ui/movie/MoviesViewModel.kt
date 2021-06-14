package com.emanuel.yourmovie.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emanuel.yourmovie.data.MoviesResult
import com.emanuel.yourmovie.data.model.Genre
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.data.repository.MoviesRepository


class MoviesViewModel(private val dataSource: MoviesRepository) : ViewModel() {

    val similarMoviesLiveData: MutableLiveData<List<SimilarMovies>> = MutableLiveData()
    val viewFlipperSimilarMoviesLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()
    val movieLiveData: MutableLiveData<Movie> = MutableLiveData()
    val viewFlipperMovieLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()
    val genreLiveData: MutableLiveData<List<Genre>> = MutableLiveData()

    companion object {
        private const val VIEW_FLIPPER_MOVIE = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    fun getMovieDetails() {
        dataSource.getMovieDetails { result: MoviesResult ->
            when(result) {
                is MoviesResult.SuccessMovieDetails -> {
                    movieLiveData.value = result.movie
                    viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)
                }
                is MoviesResult.ApiErrorMovie -> {
                    if (result.statusCode == 401) {
                        viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                    } else {
                        viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro não tratado.")
                    }
                }
                is MoviesResult.ServerError -> {
                    viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro Catastrófico.")
                }
            }
        }
    }

    fun getSimilarMovies() {
        dataSource.getSimilarMovies { result: MoviesResult ->
            when(result) {
                is MoviesResult.SuccessSimilarMovies -> {
                    similarMoviesLiveData.value = result.similarMovies
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)
                }
                is MoviesResult.ApiErrorMovie -> {
                    if (result.statusCode == 401) {
                        viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                    } else {
                        viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro não tratado.")
                    }
                }
                is MoviesResult.ServerError -> {
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro Catastrófico.")
                }
            }
        }
    }

    fun getGenre() {
        dataSource.getGenre { result: MoviesResult ->
            when(result) {
                is MoviesResult.SuccessGenre -> {
                    genreLiveData.value = result.genres
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)
                }
                is MoviesResult.ApiErrorMovie -> {
                    if (result.statusCode == 401) {
                        viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                    } else {
                        viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro não tratado.")
                    }
                }
                is MoviesResult.ServerError -> {
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Erro Catastrófico.")
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: MoviesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
                return MoviesViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknow ViewModel class")
        }

    }
}