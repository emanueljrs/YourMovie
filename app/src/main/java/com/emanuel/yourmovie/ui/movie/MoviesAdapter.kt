package com.emanuel.yourmovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.databinding.MovieListItemBinding

class MoviesAdapter(
    val similiarMovies: List<SimilarMovies>
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var binding: MovieListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(similiarMovies[position])
    }

    override fun getItemCount(): Int = similiarMovies.size

    class MoviesViewHolder(binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.textViewListMovieName
        val year = binding.textViewListMovieYear
        val category = binding.textViewListMovieCategory

        fun bind(movies: SimilarMovies) {
            name.text = movies.name
            year.text = movies.year
            category.text = movies.category
        }
    }
}