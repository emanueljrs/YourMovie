package com.emanuel.yourmovie.data.response.movie

import com.emanuel.yourmovie.data.model.Movie

data class MovieBodyResponse(

    val id: Int,
    val title: String,
    val vote_count: Int,
    val popularity: Float,
    val poster_path: String?,

) {
    fun getMovie() = Movie(
        id = this.id,
        title = this.title,
        vote_count = this.vote_count,
        popularity = this.popularity,
        poster_path = this.poster_path
    )
}
