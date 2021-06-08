package com.emanuel.yourmovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.yourmovie.data.model.Genre
import com.emanuel.yourmovie.data.model.SimilarMovies
import com.emanuel.yourmovie.databinding.MovieListItemBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val similarMovies: List<SimilarMovies>,
    private val genres: List<Genre>?
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var binding: MovieListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding, genres)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(similarMovies[position])
    }

    override fun getItemCount(): Int = similarMovies.size

    class MoviesViewHolder(binding: MovieListItemBinding, val genresList: List<Genre>?) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.textViewListMovieName
        private val release_date = binding.textViewListMovieReleaseDate
        private val poster_path = binding.imageViewListMovie
        private val genreMovie = binding.textViewListMovieGenres


        fun bind(movie: SimilarMovies) {
            title.text = movie.title
            release_date.text = movie.release_date.substring(0, 4)
            Picasso.get().load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(poster_path)

            var genresCommons: List<Genre> = mutableListOf()
            if (genresList != null) {
                genresCommons = genresList.filter { movie.genre_ids.contains(it.id) }
            }

            val genresNames: MutableList<String> = mutableListOf()
            for (genre in genresCommons) {
                genresNames.add(genre.name)
            }

            genreMovie.text = genresNames.joinToString()
        }
    }
}