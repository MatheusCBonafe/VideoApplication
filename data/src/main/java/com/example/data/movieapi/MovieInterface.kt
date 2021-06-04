package com.example.data.movieapi

import retrofit2.Call
import retrofit2.http.GET


interface MovieInterface {
    @GET("movie/popular?api_key=41156bbb6aee2f73fbc3a9ae0da544df")
    fun getData(): Call<MovieIndexDataClass>
}