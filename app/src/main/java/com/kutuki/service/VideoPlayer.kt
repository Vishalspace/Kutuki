package com.kutuki.service

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kutuki.model.Video
import javax.inject.Inject

class VideoPlayer @Inject constructor(context: Context) {
    val player = SimpleExoPlayer.Builder(context).build().apply { playWhenReady = true }

    fun play(video: Video) {
        player.setMediaItem(MediaItem.fromUri(video.videoURL))
        player.prepare()
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
        player.release()
        player.clearMediaItems()
    }
}