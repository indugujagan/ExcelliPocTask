package com.example.samplepoc.viemodel

import androidx.lifecycle.MutableLiveData
import com.example.samplepoc.api.ApiConstants
import com.example.samplepoc.api.ApiEngine
import com.example.samplepoc.model.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragVM : BaseVM() {

    val movieDetailsResponse = MutableLiveData<MovieDetailResponse>()

    /**
     * we get complete movie details in this function
     *After the API call by using live data we updating the response in UI
     * @param imdbId - each movie has unique imdbid to get complete movie details
     */
    fun getMovieDetailsApiCall(imdbId: String?) {
        setShowProgress(true)
        val call = ApiEngine.apiInterface.getMovieDetails(imdbId, ApiConstants.API_KEY)
        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                setShowProgress(false)
                showDialog(t)
            }

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                setShowProgress(false)
                movieDetailsResponse.value = response.body()
            }
        })
    }

}