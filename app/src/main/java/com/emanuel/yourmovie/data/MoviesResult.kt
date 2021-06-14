package com.emanuel.yourmovie.data

import com.emanuel.yourmovie.data.model.Genre
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.model.SimilarMovies

sealed class MoviesResult {
    class SuccessSimilarMovies(val similarMovies: List<SimilarMovies>) : MoviesResult()
    class SuccessMovieDetails(val movie: Movie?) : MoviesResult()
    class SuccessGenre(val genres: List<Genre>) : MoviesResult()
    class ApiErrorMovie(val statusCode: Int) : MoviesResult()
    object ServerError : MoviesResult()
}
