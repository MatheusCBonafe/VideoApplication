package com.example.videoapplication.list;

import MovieRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.movieapi.MovieDataClass


class ListViewModel : ViewModel() {
    private var _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private var _recyclerItems = MutableLiveData<List<RecyclerItem>>()
    val recyclerItems: LiveData<List<RecyclerItem>> get() = _recyclerItems

    private val movieRepository by lazy {
        MovieRepository()
    }

    fun movieMapper(movieList: List<MovieDataClass>): List<RecyclerItem> {

        return movieList.map {
            RecyclerItem(
                "https://image.tmdb.org/t/p/original/" + it.poster_path,
                it.original_title,
                it.overview,
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

