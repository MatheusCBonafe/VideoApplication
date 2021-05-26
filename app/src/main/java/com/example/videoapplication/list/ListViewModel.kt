package com.example.videoapplication.list;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
     //   value = "This is List Fragment"
    }
}

