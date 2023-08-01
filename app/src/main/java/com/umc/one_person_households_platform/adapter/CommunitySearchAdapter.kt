package com.umc.one_person_households_platform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommunityPostlistDTO

class CommunitySearchAdapter : ListAdapter<CommunityPostlistDTO, CommunitySearchAdapter.PostViewHolder>(PostDiffCallback()) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: CommunityPostlistDTO) {


            itemView.findViewById<TextView>(R.id.tv_posttitle).text = post.postName
            itemView.findViewById<TextView>(R.id.tv_author).text = post.author
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<CommunityPostlistDTO>() {
        override fun areItemsTheSame(oldItem: CommunityPostlistDTO, newItem: CommunityPostlistDTO): Boolean {
            return oldItem.postName == newItem.postName
        }

        override fun areContentsTheSame(oldItem: CommunityPostlistDTO, newItem: CommunityPostlistDTO): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
