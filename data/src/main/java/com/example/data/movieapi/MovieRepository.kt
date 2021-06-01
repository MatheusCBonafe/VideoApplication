package com.example.data.movieapi

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

    fun getItemData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MovieDataClass>?> {
            override fun onFailure(call: Call<List<MovieDataClass>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }

            override fun onResponse(
                call: Call<List<MovieDataClass>?>,
                response: Response<List<MovieDataClass>?>
            ) {
                val responseBody = response.body()!!
                val stringBuilder = StringBuilder()

                for (Data in responseBody) {
                    stringBuilder.append(Data.id)
                    stringBuilder.append(" - ")
                    stringBuilder.append(Data.title)
                    stringBuilder.append("\n")
                }

            }
        })
    }
}