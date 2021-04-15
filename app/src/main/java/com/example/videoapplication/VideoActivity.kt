package com.example.videoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapplication.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}