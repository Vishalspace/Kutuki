package com.kutuki.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutuki.databinding.VideoItemBinding
import com.kutuki.model.Video
import com.kutuki.model.VideosMap
import com.kutuki.utils.Logger
import com.kutuki.utils.inflater

class VideosAdapter constructor(
    private val context: Context,
    private val onClick: (Pair<String, Video>) -> Unit
) : RecyclerView.Adapter<VideoViewHolder>() {
    val videos = mutableListOf<Pair<String, Video>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoItemBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        Glide.with(context).load(video.second.thumbnailURL).into(holder.binding.videothumbnail)
        holder.binding.root.setOnClickListener { onClick(video) }
//        holder.itemView.exo
    }

    override fun getItemCount(): Int = videos.size

    fun updateData(videosMap: VideosMap) {
        logger.debug("updating data to: ${videosMap.videos}")
        videos.clear()
        videos.addAll(videosMap.videos.toList())
        notifyDataSetChanged()
    }

    fun firstItem(): Video? {
        return videos.firstOrNull()?.second
    }

    companion object {
        private val logger = Logger(VideosAdapter::class.java.name)
    }
}

class VideoViewHolder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root)