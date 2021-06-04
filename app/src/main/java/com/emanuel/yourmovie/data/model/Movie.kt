package com.emanuel.yourmovie.data.model

data class Movie(
    val id: Int,
    val title: String,
    val vote_count: Int,
    val popularity: Float,
    val poster_path: String?
)
