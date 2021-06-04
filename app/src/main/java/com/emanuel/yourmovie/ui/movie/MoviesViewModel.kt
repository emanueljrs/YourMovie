package com.emanuel.yourmovie.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuel.yourmovie.data.TheMovieApi
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.data.response.MovieBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel : ViewModel() {

     val similarMoviesLiveData: MutableLiveData<List<SimilarMovies>> = MutableLiveData()
     val moviesLiveData: MutableLiveData<Movie> = MutableLiveData()

    fun getMovieDetails() {
        TheMovieApi.retrofit.getMovieDetails(move_id = 567189).enqueue(object: Callback<MovieBodyResponse>{
            override fun onResponse(call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                if (response.isSuccessful) {
                    moviesLiveData.value = response.body()?.getMovie()
                    Log.e("Foto", "${response.body()?.poster_path}")
                } else {
                    Log.i("Falha", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
                Log.e("Erro", "Erro de comunicação: $t")
            }

        })
    }

    fun getSimilarMovies() {
        similarMoviesLiveData.value = getFakeSimiliarMovies()
    }

    private fun getFakeSimiliarMovies(): List<SimilarMovies> {
        return listOf(
            SimilarMovies(
                "Edward Scissorhands",
                "1990",
                "Drama, Fanatsy"
            ),
            SimilarMovies(
                "Ed Wood",
                "1994",
                "Comedy, Drama"
            ),
            SimilarMovies(
                "A Nightmare on Elm Street",
                "1984",
                "Horror"
            ),
            SimilarMovies(
                "A Nightmare on Elm Street",
                "1984",
                "Horror"
            ),
            SimilarMovies(
                "A Nightmare on Elm Street",
                "1984",
                "Horror"
            ),
        )

    }

}