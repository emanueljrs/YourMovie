package com.emanuel.yourmovie.ui.movie

import android.util.Log
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
     val movieLiveData: MutableLiveData<Movie> = MutableLiveData()
     val genreLiveData: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getMovieDetails() {
        TheMovieApi.retrofit.getMovieDetails().enqueue(object: Callback<MovieBodyResponse>{
            override fun onResponse(call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                if (response.isSuccessful) {
                    movieLiveData.value = response.body()?.getMovie()
                } else if (response.code() == 401) {

                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
                Log.e("Erro", "Erro de comunicação: $t")
            }

        })
    }

    fun getSimilarMovies() {

        TheMovieApi.retrofit.getSimilarMovies().enqueue(object : Callback<SimilarMoviesBodyResponse> {
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
                    Log.i("Succe", "$similarMovies")
                    similarMoviesLiveData.value = similarMovies
                } else {
                    Log.i("Falha", "${response.code()}")
                }

            }

            override fun onFailure(call: Call<SimilarMoviesBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getGenre() {
        TheMovieApi.retrofit.getGenre().enqueue(object : Callback<GenresBodyResponse> {
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
                    Log.i("Succe", "$genres")
                    genreLiveData.value = genres
                }
            }

            override fun onFailure(call: Call<GenresBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}