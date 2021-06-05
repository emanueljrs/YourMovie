package com.emanuel.yourmovie.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuel.yourmovie.data.TheMovieApi
import com.emanuel.yourmovie.data.model.Movie
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.data.response.MovieBodyResponse
import com.emanuel.yourmovie.data.response.SimilarMoviesBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel : ViewModel() {

     val similarMoviesLiveData: MutableLiveData<List<SimilarMovies>> = MutableLiveData()
     val moviesLiveData: MutableLiveData<Movie> = MutableLiveData()

    fun getMovieDetails() {
        TheMovieApi.retrofit.getMovieDetails().enqueue(object: Callback<MovieBodyResponse>{
            override fun onResponse(call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                if (response.isSuccessful) {
                    moviesLiveData.value = response.body()?.getMovie()
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
}