package com.kutuki.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutuki.R
import com.kutuki.databinding.VideoItemBinding
import com.kutuki.model.Video
import com.kutuki.utils.inflater

class VideosAdapter constructor(
    private val context: Context,
    private val onClick: (Pair<String, Video>) -> Unit
) : ListAdapter<Pair<String, Video>, VideoViewHolder>(VideosDiffCallBack()) {
    private var selectedVideoId: String = "NO_ID"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoItemBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        Glide.with(context).load(video.second.thumbnailURL).into(holder.binding.videothumbnail)
        if (video.first == selectedVideoId) {
            holder.itemView.setBackgroundResource(R.drawable.selected_item_background)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.transparent_item_background)
        }
        holder.binding.root.setOnClickListener {
            selectedVideoId = video.first
            notifyDataSetChanged()
            onClick(video)
        }
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