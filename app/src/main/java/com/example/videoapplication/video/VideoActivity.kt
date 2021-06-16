package com.example.videoapplication.video

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapplication.databinding.ActivityVideoBinding
import com.example.videoapplication.list.RecyclerItem
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util

class VideoActivity : AppCompatActivity() {


    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    val binding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getSerializableExtra("itemList")?.let {
            videoViewModel.setRecyclerItem(it as RecyclerItem)
        }

        videoViewModel.videoItem.observe(this, Observer {
            setVideoText(it)
        })
        initPlayer()
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player == null) {
            initPlayer()
            hideSystemUi()
        }
    }

    override fun onPause() {
        if (Util.SDK_INT < 24) releasePlayer()
        super.onPause()
    }

    override fun onStop() {
        if (Util.SDK_INT < 24) releasePlayer()
        super.onStop()
    }

    override fun onDestroy() {
        if (Util.SDK_INT < 24) releasePlayer()
        super.onDestroy()
    }

    private fun releasePlayer() {
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null

        }
    }

    private fun hideSystemUi() {
        player!!.release()
    }

    private fun initPlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding.VideoActivityVideoView.player = player

        var mediaItem: MediaItem =
            MediaItem.fromUri("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4")
        player!!.setMediaItem(mediaItem)
        player!!.setPlayWhenReady(playWhenReady);
        player!!.seekTo(currentWindow, playbackPosition);
        player!!.prepare();
    }

    private fun setVideoText(recyclerItem : RecyclerItem) {
        binding.videoActivityMovieTitle.text = recyclerItem.text1
        binding.videoActivityReleaseDate.text = recyclerItem.releaseDate
        binding.videoActivityMovieDescription.text = recyclerItem.text2
    }
 }