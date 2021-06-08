package com.emanuel.yourmovie.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuel.yourmovie.data.TheMovieApi
import com.emanuel.yourmovie.data.model.Genre
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.data.response.genre.GenresBodyResponse
import com.emanuel.yourmovie.data.response.movie.MovieBodyResponse
import com.emanuel.yourmovie.data.response.similar.SimilarMoviesBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel : ViewModel() {

    val similarMoviesLiveData: MutableLiveData<List<SimilarMovies>> = MutableLiveData()
    val viewFlipperSimilarMoviesLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()
    val movieLiveData: MutableLiveData<Movie> = MutableLiveData()
    val viewFlipperMovieLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()
    val genreLiveData: MutableLiveData<List<Genre>> = MutableLiveData()

    companion object {
        private const val VIEW_FLIPPER_MOVIE = 1
        private const val VIEW_FLIPPER_ERROR = 2

        private const val API_KEY = "82d5d102552c86bbe6826720fbd38fac"
        private const val MOVIE_ID = 299534
    }

    fun getMovieDetails() {
        TheMovieApi.retrofit.getMovieDetails(movie_id = MOVIE_ID, api_key = API_KEY).enqueue(object: Callback<MovieBodyResponse>{
            override fun onResponse(call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                if (response.isSuccessful) {
                    movieLiveData.value = response.body()?.getMovie()
                    viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)

                } else if (response.code() == 401) {
                    viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                } else {
                    viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, response.message())
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
                viewFlipperMovieLiveData.value = Pair(VIEW_FLIPPER_ERROR, t.message)
            }

        })
    }

    fun getSimilarMovies() {

        TheMovieApi.retrofit.getSimilarMovies(movie_id = MOVIE_ID, api_key = API_KEY).enqueue(object : Callback<SimilarMoviesBodyResponse> {
            override fun onResponse(
                call: Call<SimilarMoviesBodyResponse>,
                response: Response<SimilarMoviesBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val similarMovies: MutableList<SimilarMovies> = mutableListOf()

                    response.body()?.let { similarMoviesBodyResponse ->
                        for (result in similarMoviesBodyResponse.results) {
                            val similar = result.getSimilarMovies()
                            similarMovies.add(similar)
                        }
                    }
                    similarMoviesLiveData.value = similarMovies
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)
                } else if (response.code() == 401){
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                } else {
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, response.message())
                }

            }

            override fun onFailure(call: Call<SimilarMoviesBodyResponse>, t: Throwable) {
                viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, t.message)
            }

        })
    }

    fun getGenre() {
        TheMovieApi.retrofit.getGenre(api_key = API_KEY).enqueue(object : Callback<GenresBodyResponse> {
            override fun onResponse(
                call: Call<GenresBodyResponse>,
                response: Response<GenresBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val genres: MutableList<Genre> = mutableListOf()

                    response.body()?.let { genresBodyResponse ->
                        for (result in genresBodyResponse.genres) {
                            val genre = result.getGenre()
                            genres.add(genre)
                        }
                    }
                    genreLiveData.value = genres
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_MOVIE, null)
                } else if (response.code() == 401){
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Error 401")
                } else {
                    viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, response.message())
                }
            }

            override fun onFailure(call: Call<GenresBodyResponse>, t: Throwable) {
                viewFlipperSimilarMoviesLiveData.value = Pair(VIEW_FLIPPER_ERROR, t.message)
            }

        })
    }
}