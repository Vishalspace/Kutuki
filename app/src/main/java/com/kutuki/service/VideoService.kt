package com.kutuki.service

import com.kutuki.api.KutukiApi
import com.kutuki.model.CategoriesMap
import com.kutuki.model.VideosMap
import com.kutuki.utils.Logger
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoService @Inject constructor(private val api: KutukiApi) {

    fun getCategories(): Single<CategoriesMap> {
        return api.getCategories().map { it.response }.subscribeOn(Schedulers.io())
    }

    fun getVideos(): Single<VideosMap> {
        return api.getVideos().map { it.response }.subscribeOn(Schedulers.io())
    }

    companion object {
        private val logger = Logger(VideoService::class.java.name)
    }
}
