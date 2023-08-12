package com.umc.one_person_households_platform.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.databinding.ItemHomeGroupBuyingListBinding
import com.umc.one_person_households_platform.model.GroupBuyingContent

class HomeGroupBuyingAdapter : ListAdapter<GroupBuyingContent, HomeGroupBuyingAdapter.ViewHolder>(GroupBuyingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeGroupBuyingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemHomeGroupBuyingListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GroupBuyingContent) {
            with(binding) {
                groupBuying = item

                Glide.with(itemView)
                    .load(item.imagePath)
                    .into(sivPhoto)

                executePendingBindings()
            }
        }
    }
}

class GroupBuyingDiffCallback : DiffUtil.ItemCallback<GroupBuyingContent>() {
    override fun areItemsTheSame(oldItem: GroupBuyingContent, newItem: GroupBuyingContent): Boolean {
        return oldItem.postIdx == newItem.postIdx
    }

    override fun areContentsTheSame(oldItem: GroupBuyingContent, newItem: GroupBuyingContent): Boolean {
        return oldItem == newItem
    }
}