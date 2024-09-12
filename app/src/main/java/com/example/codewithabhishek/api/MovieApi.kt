package com.example.codewithabhishek.api

import com.example.codewithabhishek.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun getMovies(
            @Query("s") searchQuery: String,
            @Query("apikey") apiKey: String,
            @Query("type") type: String
    ): MovieResponse
}
