package com.kutuki.service

import com.kutuki.api.KutukiApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoService @Inject constructor(private val api: KutukiApi) {

    init {
        val disp = api.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.response.videoCategories.toList()
                logger.loge("response: $it")
            }, {
                logger.loge("error making api call: $it")
            })
        logger.loge(disp.toString())
    }

    init {
        val disp1 = api.getVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                logger.loge("response: $it")
            }, {
                logger.loge("error making api call: $it")
            })
        logger.loge(disp1.toString())
    }

    companion object {
        private val logger = Logger(VideoService::class.java.name)
    }
}
