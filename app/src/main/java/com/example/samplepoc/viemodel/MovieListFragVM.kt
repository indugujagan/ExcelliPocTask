package com.example.samplepoc.viemodel

import androidx.lifecycle.MutableLiveData
import com.example.samplepoc.api.ApiConstants
import com.example.samplepoc.api.ApiEngine
import com.example.samplepoc.model.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragVM : BaseVM() {

    val movieListResponse = MutableLiveData<MovieListResponse>()
    fun getMoviesApiCall(movieTitle: String, page: Int) {
        setShowProgress(true)
        val call = ApiEngine.apiInterface.getMovies(ApiConstants.API_KEY,movieTitle,page)
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                setShowProgress(false)
                showDialog(t)
            }

            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                setShowProgress(false)
                movieListResponse.value = response.body()
            }
        })
    }
}