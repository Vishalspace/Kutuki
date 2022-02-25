package com.kutuki.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutuki.databinding.CategoriesItemBinding

import com.kutuki.model.CategoriesMap
import com.kutuki.model.Category
import com.kutuki.utils.Logger
import com.kutuki.utils.inflater

class CategoriesAdapter constructor(
    private val context: Context,
    private val onClick: (Pair<String, Category>) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {
    val categories = mutableListOf<Pair<String, Category>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoriesItemBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        Glide.with(context).load(category.second.image).into(holder.binding.categorythumbnail)
        holder.binding.root.setOnClickListener { onClick(category) }
    }

    override fun getItemCount(): Int = categories.size

    fun updateData(categoriesMap: CategoriesMap) {
        logger.debug("updating data to: ${categoriesMap.videoCategories}")
        categories.clear()
        categories.addAll(categoriesMap.videoCategories.toList())
        notifyDataSetChanged()
    }

    companion object {
        private val logger = Logger(CategoriesAdapter::class.java.name)
    }
}

class CategoryViewHolder(val binding: CategoriesItemBinding) : RecyclerView.ViewHolder(binding.root)