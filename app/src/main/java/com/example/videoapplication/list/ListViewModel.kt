package com.example.videoapplication.list;

import MovieRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.movieapi.MovieDataClass


class ListViewModel : ViewModel() {

    private var _recyclerItems = MutableLiveData<List<RecyclerItem>>()
    val recyclerItems: LiveData<List<RecyclerItem>> get() = _recyclerItems

    private val movieRepository by lazy {
        MovieRepository()
    }

    fun movieMapper(movieList: List<MovieDataClass>): List<RecyclerItem> {

        return movieList.map {
            RecyclerItem(
                it.id,
                "https://image.tmdb.org/t/p/original/" + it.poster_path,
                it.original_title,
                it.overview,
                it.release_date,
                true
            )
        }
    }

    fun text() {
        movieRepository.getItemData {
            _recyclerItems.value = movieMapper(it)
        }
    }
}

