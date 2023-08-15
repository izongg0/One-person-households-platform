package com.umc.one_person_households_platform.view.groupbuying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.databinding.ItemGroupBuyingContentBinding
import com.umc.one_person_households_platform.model.GroupBuyingContent

class GroupBuyingAdapter : PagingDataAdapter<GroupBuyingContent, GroupBuyingAdapter.ViewHolder>(GroupBuyingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGroupBuyingContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemGroupBuyingContentBinding) : RecyclerView.ViewHolder(binding.root) {
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