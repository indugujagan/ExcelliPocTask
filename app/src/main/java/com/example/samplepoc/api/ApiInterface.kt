package com.example.samplepoc.api

import com.example.samplepoc.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(".")
    fun getMovies(@Query("apikey") api_key: String?,
                  @Query("s") movie_title: String?,
                  @Query("page") page: Int): Call<MovieListResponse>
}