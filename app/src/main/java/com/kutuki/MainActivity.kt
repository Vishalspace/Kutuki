package com.kutuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kutuki.service.Logger
import com.kutuki.service.VideoService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var videoService: VideoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)
        logger.loge("service: $videoService")
    }

    companion object {
        private val logger = Logger(MainActivity::class.java.name)
    }
}