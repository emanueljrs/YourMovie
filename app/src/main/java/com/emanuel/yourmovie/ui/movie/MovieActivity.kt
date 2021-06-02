package com.emanuel.yourmovie.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding.recyclerSimilarMovies) {
            layoutManager = LinearLayoutManager(this@MovieActivity, RecyclerView.VERTICAL, false)
            adapter = MoviesAdapter(getSimiliarMovies())
        }
    }

    private fun getSimiliarMovies(): List<SimilarMovies> {
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