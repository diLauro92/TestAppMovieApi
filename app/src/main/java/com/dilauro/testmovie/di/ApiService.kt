package com.dilauro.testmovie.di

import com.dilauro.testmovie.data.dataclasses.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reviews/all.json")
    fun getMovies(@Query("api-key") privateKey: String, @Query("offset") offset: Int): Call<Movies>

}