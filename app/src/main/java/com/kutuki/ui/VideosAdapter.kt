package com.kutuki.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutuki.databinding.VideoItemBinding
import com.kutuki.model.Video
import com.kutuki.utils.inflater

class VideosAdapter constructor(
    private val context: Context,
    private val onClick: (Pair<String, Video>) -> Unit
) : ListAdapter<Pair<String, Video>, VideoViewHolder>(VideosDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoItemBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        Glide.with(context).load(video.second.thumbnailURL).into(holder.binding.videothumbnail)
        holder.binding.root.setOnClickListener { onClick(video) }
    }

    fun firstItem(): Video? {
        return if (itemCount > 0) getItem(0).second else null
    }
}

class VideoViewHolder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root)

class VideosDiffCallBack : DiffUtil.ItemCallback<Pair<String, Video>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, Video>,
        newItem: Pair<String, Video>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Video>,
        newItem: Pair<String, Video>
    ): Boolean {
        return oldItem.first == newItem.first && oldItem.second == newItem.second
    }

}