package com.example.readorwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readorwatch.api.ApiClient
import com.example.readorwatch.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {

    private val _openDetailsEvent = MutableLiveData(OpenDetailsEvent(null))
    val openDetailsEvent: LiveData<OpenDetailsEvent> = _openDetailsEvent

    private val _openHomeEvent = MutableLiveData(OpenDetailsEvent(null))
    val openHomeEvent: LiveData<OpenDetailsEvent> = _openHomeEvent

    private val _homeMoviesList = MutableLiveData<List<MovieItem>>()
    val popularMoviesList: LiveData<List<MovieItem>> = _homeMoviesList

    private val _apiErrorHome = MutableLiveData<String?>(null)
    val apiErrorHome: LiveData<String?> = _apiErrorHome

    private val _genresList = MutableLiveData<List<Genres>>()
    val genresList: LiveData<List<Genres>> = _genresList

    private val _apiErrorGenres = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiErrorGenres

    init {
        fetchPopular()
    }

    fun setDetailsEvent(detailsEvent: OpenDetailsEvent) {
        _openDetailsEvent.value = detailsEvent
    }

    fun fetchPopular() {
        ApiClient().getService()?.fetchPopular()?.enqueue(object : Callback<List<MovieResponse>> {
            override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                _apiErrorHome.value = t.message
            }

            override fun onResponse(
                call: Call<List<MovieResponse>>,
                response: Response<List<MovieResponse>>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _apiErrorHome.value = null
                        _homeMoviesList.value =
                            MovieResponseParser.parseMovieResponseToMovieItem(responseBody)
                    }
                } else {
                    _apiErrorHome.value = response.code().toString()
                }
            }
        })
    }

    fun fetchGenres() {
        ApiClient().getService()?.fetchGenres()?.enqueue(object : Callback<GenresResponse> {
            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                _apiErrorGenres.value = t.message
            }

            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody?.genres != null) {
                        _genresList.value = responseBody.genres
                        _apiErrorGenres.value = null
                    }
                } else {
                    _apiErrorGenres.value = response.code().toString()
                }
            }
        })
    }

    fun fetchGenreMembers(genreId: Int) {
        ApiClient().getService()?.fetchGenreMembers(genreId)?.enqueue(object : Callback<List<MovieResponse>> {
            override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                _apiErrorGenres.value = t.message
            }

            override fun onResponse(
                call: Call<List<MovieResponse>>,
                response: Response<List<MovieResponse>>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _homeMoviesList.value =
                            MovieResponseParser.parseMovieResponseToMovieItem(responseBody)
                        _apiErrorGenres.value = null
                    }
                } else {
                    _apiErrorGenres.value = response.code().toString()
                }
            }
        })
    }
}