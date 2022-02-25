package com.kutuki

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kutuki.databinding.VideosActivityBinding
import com.kutuki.model.Category
import com.kutuki.model.Video
import com.kutuki.model.VideosMap
import com.kutuki.service.VideoService
import com.kutuki.ui.VideosAdapter
import com.kutuki.utils.Logger
import com.kutuki.utils.addTo
import com.kutuki.utils.injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VideosActivity : AppCompatActivity() {
    @Inject
    lateinit var videoService: VideoService
    private lateinit var adapter: VideosAdapter
    private lateinit var binding: VideosActivityBinding
    private val compositeDisposable = CompositeDisposable()

    private lateinit var simpleExoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector().inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.videos_activity)
        initUi()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun initUi() {
        adapter = VideosAdapter(this) {
            simpleExoPlayer.stop()
            simpleExoPlayer.release()
            updateMainVideoSource(it.second)
        }
        binding.videosRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.videosRecyclerView.adapter = adapter
        binding.vaBackButton.setOnClickListener { onBackPressed() }
    }

    private fun updateMainVideoSource(video: Video) {
        Glide.with(this).load(video.thumbnailURL).into(binding.currentVideoThumbnail)
        initPlayer(video)
    }

    private fun onVideosLoaded(videosMap: VideosMap) {
        adapter.updateData(videosMap)
        adapter.firstItem()?.let { updateMainVideoSource(it) }
    }

    private fun initPlayer(video: Video) {
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        binding.exoplayerView.player = simpleExoPlayer
        val mediaItem = MediaItem.fromUri(video.videoURL)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
    }


    override fun onResume() {
        super.onResume()
        videoService.getVideos().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onVideosLoaded(it) },
                { logger.error("Error fetching categories", it) }
            ).addTo(compositeDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
        simpleExoPlayer.pause()
        simpleExoPlayer.playWhenReady = false
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.pause()
        simpleExoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        simpleExoPlayer.stop()
        simpleExoPlayer.clearMediaItems()
    }

    companion object {
        private const val CATEGORY_NAME = "intent_category_name"
        private val logger = Logger(MainActivity::class.java.name)

        fun getIntent(context: Context, category: Category): Intent {
            return Intent(context, VideosActivity::class.java).putExtra(
                CATEGORY_NAME,
                category.name
            )
        }
    }

}