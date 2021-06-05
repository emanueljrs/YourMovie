package com.emanuel.yourmovie.data.response

import com.emanuel.yourmovie.data.model.SimilarMovies

data class SimilarMoviesResultsResponse(
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path: String?
){
    fun getSimilarMovies() = SimilarMovies(
        id = this.id,
        title = this.title,
        release_date = this.release_date,
        poster_path = this.poster_path
    )
}
