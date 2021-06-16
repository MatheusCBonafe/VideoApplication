package com.example.videoapplication.video;

import MovieRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.movieapi.MovieDataClass
import com.example.videoapplication.list.RecyclerItem

class VideoViewModel : ViewModel() {

    private var _videoItem = MutableLiveData<RecyclerItem>()
    val videoItem: LiveData<RecyclerItem> get() = _videoItem

    fun setRecyclerItem (recyclerItem : RecyclerItem) {
        _videoItem.value = recyclerItem
    }
}



