package com.emanuel.yourmovie.data

import com.emanuel.yourmovie.data.response.MovieBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieServices {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") move_id: Int = 550,
        @Query("api_key") api_key: String = "82d5d102552c86bbe6826720fbd38fac"
    ): Call<MovieBodyResponse>
}