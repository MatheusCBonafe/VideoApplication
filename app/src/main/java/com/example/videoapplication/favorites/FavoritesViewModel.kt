package com.example.videoapplication.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Favorites Fragment"
    }
}