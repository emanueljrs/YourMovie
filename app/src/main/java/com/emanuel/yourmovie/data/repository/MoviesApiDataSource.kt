package com.emanuel.yourmovie.data.repository

import com.emanuel.yourmovie.data.MoviesResult
import com.emanuel.yourmovie.data.TheMovieApi
import com.emanuel.yourmovie.data.model.Genre
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.data.response.genre.GenresBodyResponse
import com.emanuel.yourmovie.data.response.movie.MovieBodyResponse
import com.emanuel.yourmovie.data.response.similar.SimilarMoviesBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesApiDataSource : MoviesRepository {

    private val API_KEY = "82d5d102552c86bbe6826720fbd38fac"
    private val MOVIE_ID = 299534

    override fun getMovieDetails(movieResultCallback: (result: MoviesResult) -> Unit) {
        TheMovieApi.retrofit.getMovieDetails(movie_id = MOVIE_ID, api_key = API_KEY).enqueue(object:
            Callback<MovieBodyResponse> {
            override fun onResponse(call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        movieResultCallback(MoviesResult.SuccessMovieDetails(response.body()?.getMovie()))

                    }
                    else -> {
                        movieResultCallback(MoviesResult.ApiErrorMovie(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
               movieResultCallback(MoviesResult.ServerError)
            }
        })
    }

    override fun getSimilarMovies(similarMovieResultCallback: (result: MoviesResult) -> Unit) {
        TheMovieApi.retrofit.getSimilarMovies(movie_id = MOVIE_ID, api_key = API_KEY).enqueue(object : Callback<SimilarMoviesBodyResponse> {
            override fun onResponse(
                call: Call<SimilarMoviesBodyResponse>,
                response: Response<SimilarMoviesBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val similarMovies: MutableList<SimilarMovies> = mutableListOf()

                        response.body()?.let { similarMoviesBodyResponse ->
                            for (result in similarMoviesBodyResponse.results) {
                                val similar = result.getSimilarMovies()
                                similarMovies.add(similar)
                            }
                        }
                        similarMovieResultCallback(MoviesResult.SuccessSimilarMovies(similarMovies))
                    }
                    else -> {
                        similarMovieResultCallback(MoviesResult.ApiErrorMovie(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<SimilarMoviesBodyResponse>, t: Throwable) {
                similarMovieResultCallback(MoviesResult.ServerError)
            }

        })
    }

    override fun getGenre(genreMovieResultCallback: (result: MoviesResult) -> Unit) {
        TheMovieApi.retrofit.getGenre(api_key = API_KEY).enqueue(object : Callback<GenresBodyResponse> {
            override fun onResponse(
                call: Call<GenresBodyResponse>,
                response: Response<GenresBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val genres: MutableList<Genre> = mutableListOf()

                        response.body()?.let { genresBodyResponse ->
                            for (result in genresBodyResponse.genres) {
                                val genre = result.getGenre()
                                genres.add(genre)
                            }
                        }
                        genreMovieResultCallback(MoviesResult.SuccessGenre(genres))
                    }
                    else -> {
                        genreMovieResultCallback(MoviesResult.ApiErrorMovie(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<GenresBodyResponse>, t: Throwable) {
               genreMovieResultCallback(MoviesResult.ServerError)
            }

        })
    }
}