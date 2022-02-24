package com.kutuki.api

import com.kutuki.model.CategoryResponse
import com.kutuki.model.VideoResponse
import retrofit2.http.GET
import io.reactivex.Observable
import io.reactivex.Single

interface KutukiApi {
    @GET("5e2bebd23100007a00267e51")
    fun getCategories(): Single<CategoryResponse>

    @GET("5e2beb5a3100006600267e4e")
    fun getVideos(): Single<List<VideoResponse>>
}