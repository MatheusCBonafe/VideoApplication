package com.example.videoapplication.movieapi

import retrofit2.Call
import retrofit2.http.GET


interface MovieInterface {
    @GET("posts")
    fun getData(): Call<List<MovieDataClass>>
}