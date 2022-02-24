package com.kutuki.model

data class CategoryResponse(
    val videoCategories: Map<String, VideoCategory>
)

data class VideoCategory(
    val name: String,
    val image: String
)

data class VideoResponse(
    val videos: Map<String, Video>
)

data class Video(
    val title: String,
    val description: String,
    val thumbnailURL: String,
    val videoURL: String,
    val categories: String
)