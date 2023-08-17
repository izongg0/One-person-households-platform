package com.umc.one_person_households_platform.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.databinding.ItemHomeHotRecipeListBinding
import com.umc.one_person_households_platform.model.HotRecipeContent
import com.umc.one_person_households_platform.view.common.OnClickInterface

class HomeHotRecipeAdapter(private val onClick: OnClickInterface) : ListAdapter<HotRecipeContent, HomeHotRecipeAdapter.ViewHolder>(HotRecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeHotRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemHomeHotRecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HotRecipeContent) {
            with(binding) {
                event = onClick
                hotRecipe = item

                Glide.with(itemView)
                    .load(item.imagePath)
                    .into(ivRecipeFood)

                executePendingBindings()
            }
        }
    }
}

class HotRecipeDiffCallback : DiffUtil.ItemCallback<HotRecipeContent>() {
    override fun areItemsTheSame(oldItem: HotRecipeContent, newItem: HotRecipeContent): Boolean {
        return oldItem.postIdx == newItem.postIdx
    }

    override fun areContentsTheSame(oldItem: HotRecipeContent, newItem: HotRecipeContent): Boolean {
        return oldItem == newItem
    }
}