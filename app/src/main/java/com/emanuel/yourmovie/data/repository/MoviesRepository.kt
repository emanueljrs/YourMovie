package com.emanuel.yourmovie.data.repository

import com.emanuel.yourmovie.data.MoviesResult

interface MoviesRepository {
    fun getMovieDetails(movieResultCallback: (result: MoviesResult) -> Unit)
    fun getSimilarMovies(similarMovieResultCallback: (result: MoviesResult) -> Unit)
    fun getGenre(genreMovieResultCallback: (result: MoviesResult) -> Unit)
}