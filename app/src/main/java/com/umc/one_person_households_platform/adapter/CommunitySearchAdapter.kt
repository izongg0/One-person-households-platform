package com.umc.one_person_households_platform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityPostItems
import com.umc.one_person_households_platform.model.CommunityPostlistDTO

class CommunitySearchAdapter : ListAdapter<CommunityPostItems, CommunitySearchAdapter.PostViewHolder>(PostDiffCallback()) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: CommunityDTO) {

            itemView.findViewById<TextView>(R.id.tv_posttitle).text = post!!.result.items[position].title
            itemView.findViewById<TextView>(R.id.tv_author).text = post!!.result.items[position].nickname
            itemView.findViewById<TextView>(R.id.tv_category).text = post!!.result.items[position].category
            itemView.findViewById<TextView>(R.id.tv_distance).text = post!!.result.items[position].distance.toString()
            itemView.findViewById<TextView>(R.id.tv_time).text = post!!.result.items[position].createAt


        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<CommunityPostItems>() {
        override fun areItemsTheSame(oldItem: CommunityPostItems, newItem: CommunityPostItems): Boolean {
            return oldItem.postIdx == newItem.postIdx
        }

        override fun areContentsTheSame(oldItem: CommunityPostItems, newItem: CommunityPostItems): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        holder.bind(getItem(position))
    }
}
