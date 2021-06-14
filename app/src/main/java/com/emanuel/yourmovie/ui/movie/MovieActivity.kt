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

        configureToolbar()

        binding.toggleButtonListMovieCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.toggleButtonListMovieCheck.setButtonDrawable(R.drawable.ic_favorite_movie)
            } else {
                binding.toggleButtonListMovieCheck.setButtonDrawable(R.drawable.ic_favorite_border_movie)
            }
        }

        val viewModel: MoviesViewModel by viewModels()

        viewModel.similarMoviesLiveData.observe(this) {
            it?.let { similarMovies ->
                with(binding.recyclerSimilarMovies) {
                    layoutManager = LinearLayoutManager(this@MovieActivity, RecyclerView.VERTICAL, false)
                    adapter = MoviesAdapter(similarMovies, viewModel.genreLiveData.value)
                }
            }
        }

        viewModel.movieLiveData.observe(this) {
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

        viewModel.viewFlipperMovieLiveData.observe(this) {
            it?.let { viewFlipper ->
                binding.viewFlipperMovie.displayedChild = viewFlipper.first

                viewFlipper.second?.let {
                    binding.textViewError.text = it
                }
            }
        }

        viewModel.viewFlipperSimilarMoviesLiveData.observe(this) {
            it?.let { viewFlipper ->
                binding.viewFlipperSimilarMovies.displayedChild = viewFlipper.first

                viewFlipper.second?.let {
                    binding.textViewErrorSimilar.text = it
                }
            }
        }

        viewModel.getMovieDetails()
        viewModel.getSimilarMovies()
        viewModel.getGenre()

    }

    fun configureToolbar() {
        binding.toolBarMovie.title = ""
        binding.toolBarMovie.setNavigationIcon(R.drawable.back_arrow_30)
        setSupportActionBar(binding.toolBarMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}