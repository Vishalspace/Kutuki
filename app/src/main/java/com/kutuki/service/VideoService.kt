package com.kutuki.service

import com.kutuki.api.KutukiApi
import com.kutuki.model.CategoriesMap
import com.kutuki.model.VideosMap
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoService @Inject constructor(private val api: KutukiApi) {

    fun getCategories(): Single<CategoriesMap> {
        return api.getCategories().map { it.response }.subscribeOn(Schedulers.io())
    }

    fun getVideos(categoryName: String? = null): Single<VideosMap> {
        return api.getVideos().map { r ->
            if (categoryName != null) {
                VideosMap(r.response.videos.filter { (_, video) ->
                    video.categories.contains(categoryName, true)
                })
            } else {
                r.response
            }
        }.subscribeOn(Schedulers.io())
    }
}
