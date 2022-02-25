package com.kutuki.model

data class GetCategoriesResponse(
    val response: CategoriesMap
)

data class CategoriesMap(val videoCategories: Map<String, Category>)

data class Category(
    val id: String,
    val name: String,
    val image: String
)

data class GetVideosResponse(val response: VideosMap)

data class VideosMap(
    val videos: Map<String, Video>
)

data class Video(
    val title: String,
    val description: String,
    val thumbnailURL: String,
    val videoURL: String,
    val categories: String
)