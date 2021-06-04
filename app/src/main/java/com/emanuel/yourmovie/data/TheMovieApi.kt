package com.emanuel.yourmovie.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheMovieApi {

    const val API_KEY = "82d5d102552c86bbe6826720fbd38fac"
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Create instance of the retrofit associate class TheMovieServices
    val retrofit: TheMovieServices = initRetrofit().create(TheMovieServices::class.java)

}