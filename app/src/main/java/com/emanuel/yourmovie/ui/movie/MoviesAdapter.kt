package com.emanuel.yourmovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.databinding.MovieListItemBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val similarMovies: List<SimilarMovies>
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var binding: MovieListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(similarMovies[position])
    }

    override fun getItemCount(): Int = similarMovies.size

    class MoviesViewHolder(binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val title = binding.textViewListMovieName
        val release_date = binding.textViewListMovieReleaseDate
        val poster_path = binding.imageViewListMovie


        fun bind(movie: SimilarMovies) {
            title.text = movie.title
            release_date.text = movie.release_date.substring(0, 4)
            Picasso.get().load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(poster_path)
        }
    }
}