package com.emanuel.yourmovie.ui.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.R
import com.emanuel.yourmovie.databinding.ActivityMovieBinding
import com.squareup.picasso.Picasso

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val viewModel: MoviesViewModel by viewModels()

        viewModel.similarMoviesLiveData.observe(this) {
            it?.let { similarMovies ->
                with(binding.recyclerSimilarMovies) {
                    layoutManager = LinearLayoutManager(this@MovieActivity, RecyclerView.VERTICAL, false)
                    adapter = MoviesAdapter(similarMovies)
                }
            }
        }

        viewModel.moviesLiveData.observe(this) {
            it?.let { movie ->
                binding.textViewMovieTitle.text = movie.title
                binding.textViewLikes.text = "${movie.vote_count} likes"
                binding.textViewPopularity.text = "${movie.popularity} views"
                if (movie.poster_path != null) {
                    Picasso.get().load("http://image.tmdb.org/t/p/w500${movie.poster_path}")
                        .into(binding.imageViewMovie)
                } else {
                    binding.imageViewMovie.setImageResource(R.drawable.ic_baseline_broken)
                }

            }
        }

        viewModel.getMovieDetails()
        viewModel.getSimilarMovies()
    }
}