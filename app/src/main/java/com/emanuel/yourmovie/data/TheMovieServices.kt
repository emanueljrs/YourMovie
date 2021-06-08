package com.emanuel.yourmovie.data

import com.emanuel.yourmovie.data.response.genre.GenresBodyResponse
import com.emanuel.yourmovie.data.response.movie.MovieBodyResponse
import com.emanuel.yourmovie.data.response.similar.SimilarMoviesBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieServices {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<MovieBodyResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies (
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<SimilarMoviesBodyResponse>

    @GET("genre/movie/list")
    fun getGenre (
        @Query("api_key") api_key: String
    ): Call<GenresBodyResponse>
}