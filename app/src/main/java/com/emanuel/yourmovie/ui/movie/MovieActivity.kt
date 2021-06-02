package com.emanuel.yourmovie.ui.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel: MoviesViewModel by viewModels()

        viewModel.movieLiveData.observe(this) {
            it?.let { similarMovies ->
                with(binding.recyclerSimilarMovies) {
                    layoutManager = LinearLayoutManager(this@MovieActivity, RecyclerView.VERTICAL, false)
                    adapter = MoviesAdapter(similarMovies)
                }
            }
        }

        viewModel.getSimilarMovies()
    }
}