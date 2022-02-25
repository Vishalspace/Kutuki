package com.kutuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kutuki.databinding.ActivityMainBinding
import com.kutuki.model.VideoCategories
import com.kutuki.service.Logger
import com.kutuki.service.VideoService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var videoService: VideoService

    val adapter = CategoryAdapter(VideoCategories(emptyMap()), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = GridLayoutManager(this, 2)

        (application as App).component.inject(this)
        logger.loge("service: $videoService")
    }

    companion object {
        private val logger = Logger(MainActivity::class.java.name)
    }
}