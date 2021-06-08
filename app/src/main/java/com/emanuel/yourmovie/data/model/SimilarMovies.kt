package com.emanuel.yourmovie.data.model

data class SimilarMovies(
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path: String?,
    val genre_ids: List<Int>
)