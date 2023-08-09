package com.umc.one_person_households_platform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.RecipeDTO

class RecipeSearchAdapter : ListAdapter<RecipeDTO, RecipeSearchAdapter.PostViewHolder>(PostDiffCallback()) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: RecipeDTO) {


            itemView.findViewById<TextView>(R.id.tv_recipetitle).text = post.recipe_title
            itemView.findViewById<TextView>(R.id.tv_recipetag).text = post.recipe_tag
            itemView.findViewById<TextView>(R.id.tv_scrapcount).text = post.scrap_count.toString()

        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<RecipeDTO>() {
        override fun areItemsTheSame(oldItem: RecipeDTO, newItem: RecipeDTO): Boolean {
            return oldItem.recipe_title == newItem.recipe_title
        }

        override fun areContentsTheSame(oldItem: RecipeDTO, newItem: RecipeDTO): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
