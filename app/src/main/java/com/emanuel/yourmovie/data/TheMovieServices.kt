package com.emanuel.yourmovie.data

import com.emanuel.yourmovie.data.response.MovieBodyResponse
import com.emanuel.yourmovie.data.response.SimilarMoviesBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieServices {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int = 299534,
        @Query("api_key") api_key: String = "82d5d102552c86bbe6826720fbd38fac"
    ): Call<MovieBodyResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies (
        @Path("movie_id") movie_id: Int = 299534,
        @Query("api_key") api_key: String = "82d5d102552c86bbe6826720fbd38fac"
    ): Call<SimilarMoviesBodyResponse>
}