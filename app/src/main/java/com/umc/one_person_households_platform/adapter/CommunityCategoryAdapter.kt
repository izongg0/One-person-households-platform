package com.umc.one_person_households_platform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommunityPostlistDTO

class CommunityCategoryAdapter(var postlist : ArrayList<CommunityPostlistDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return CustomViewHolder(view)
    }

    private inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {

        return postlist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var view = holder.itemView
        view.findViewById<TextView>(R.id.tv_posttitle).text = postlist[position].postName
        view.findViewById<TextView>(R.id.tv_author).text = postlist[position].author
        view.findViewById<TextView>(R.id.tv_posttitle).text = postlist[position].category

        view.findViewById<TextView>(R.id.tv_posttitle).setOnClickListener {

            Navigation.findNavController(view)
                .navigate(R.id.action_communityFragment_to_postdetailFragment)
        }

    }


}