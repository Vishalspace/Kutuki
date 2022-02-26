package com.kutuki.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutuki.databinding.CategoriesItemBinding
import com.kutuki.model.Category
import com.kutuki.utils.inflater

class CategoriesAdapter constructor(
    private val context: Context,
    private val onClick: (Pair<String, Category>) -> Unit
) : ListAdapter<Pair<String, Category>, CategoryViewHolder>(CategoriesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoriesItemBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        Glide.with(context).load(category.second.image).into(holder.binding.categorythumbnail)
        holder.binding.root.setOnClickListener { onClick(category) }
    }
}

class CategoryViewHolder(val binding: CategoriesItemBinding) : RecyclerView.ViewHolder(binding.root)

class CategoriesDiffCallBack : DiffUtil.ItemCallback<Pair<String, Category>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, Category>,
        newItem: Pair<String, Category>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Category>,
        newItem: Pair<String, Category>
    ): Boolean {
        return oldItem.first == newItem.first && oldItem.second == newItem.second
    }
}