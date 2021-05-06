package com.example.videoapplication

import android.app.Activity
import android.os.Bundle
import com.example.videoapplication.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util


class VideoActivity : Activity() {
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    val binding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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

        var mediaItem : MediaItem = MediaItem.fromUri("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4")
        player!!.setMediaItem(mediaItem)
        player!!.setPlayWhenReady(playWhenReady);
        player!!.seekTo(currentWindow, playbackPosition);
        player!!.prepare();

    }
}