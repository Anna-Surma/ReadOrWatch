package com.example.readorwatch.api

import com.example.readorwatch.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST(Constants.SIGNUP_URL)
    fun signUp(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST(Constants.SIGNIN_URL)
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignInResponse>

    @GET(Constants.GENRES_URL)
    fun fetchGenres(): Call<GenresResponse>

    @GET(Constants.POPULAR_URL)
    fun fetchPopular(): Call<List<MovieResponse>>

    @GET("/api/movies/{movieID}")
    fun getMovieDetails(@Path("movieID") movieID: Int): Call<MovieResponse>

    @GET("/api/movies/category/{id}")
    fun fetchGenreMembers(@Path("id") genreId: Int): Call<List<MovieResponse>>
}
