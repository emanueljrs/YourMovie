package com.emanuel.yourmovie.data.response.genre

import com.emanuel.yourmovie.data.model.Genre

data class GenreResultResponse(
    val id: Int,
    val name: String
) {
    fun getGenre() = Genre(
        id = this.id,
        name = this.name
    )
}
