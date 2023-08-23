package com.umc.one_person_households_platform.view.groupbuyingwrite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.databinding.ItemGroupBuyingWritePhotoBinding

class GroupBuyingWritePhotoAdapter(private val onClick: GroupBuyingWriteRemoveInterface) : ListAdapter<String, GroupBuyingWritePhotoAdapter.ViewHolder>(GroupBuyingWritePhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGroupBuyingWritePhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemGroupBuyingWritePhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                position = layoutPosition
                photo = onClick

                Glide.with(itemView)
                    .load(item)
                    .into(sivPhoto)

                executePendingBindings()
            }
        }
    }
}

class GroupBuyingWritePhotoDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}