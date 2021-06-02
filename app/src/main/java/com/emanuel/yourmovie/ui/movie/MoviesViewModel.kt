package com.emanuel.yourmovie.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuel.yourmovie.data.model.SimilarMovies


class MoviesViewModel : ViewModel() {

     val movieLiveData: MutableLiveData<List<SimilarMovies>> = MutableLiveData()

    fun getSimilarMovies() {
        movieLiveData.value = getFakeSimiliarMovies()
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