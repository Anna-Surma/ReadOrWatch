package com.example.readorwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readorwatch.api.ApiClient
import com.example.readorwatch.model.MovieItem
import com.example.readorwatch.model.MovieResponse
import com.example.readorwatch.model.MovieResponseParser.parseMovieResponseToMovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieItem>()
    val movieDetails: LiveData<MovieItem> = _movieDetails

    private val _apiError = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiError


    private var _movieId: Int? = null

    fun setApiError(error: String){
        _apiError.value = error
    }

    fun getMovieDetails(movieId: Int) {
        ApiClient().getService()?.getMovieDetails(movieId)?.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _apiError.value = t.message
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    _apiError.value = null
                    _movieDetails.value = parseMovieResponseToMovieItem(responseBody!!)
                    _movieId = movieDetails.value!!.id
                } else {
                    _apiError.value = response.code().toString()
                }
            }
        })
    }
}