package com.example.samplepoc.model


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)