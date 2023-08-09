package com.umc.one_person_households_platform.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityPost
import com.umc.one_person_households_platform.model.CommunityPostlistDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityCategoryAdapter(var realpostlist: CommunityDTO) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return CustomViewHolder(view)
    }

    private inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {

        return realpostlist?.result?.items?.size ?: 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        var view = holder.itemView


        view.findViewById<TextView>(R.id.tv_posttitle).text =
            realpostlist!!.result.items[position].title
        view.findViewById<TextView>(R.id.tv_author).text =
            realpostlist!!.result.items[position].nickname
        view.findViewById<TextView>(R.id.tv_category).text =
            realpostlist!!.result.items[position].category
        view.findViewById<TextView>(R.id.tv_distance).text =
            realpostlist!!.result.items[position].distance.toString()
        view.findViewById<TextView>(R.id.tv_time).text =
            realpostlist!!.result.items[position].createAt





        view.findViewById<TextView>(R.id.tv_posttitle).setOnClickListener {

            Navigation.findNavController(view)
                .navigate(R.id.action_communityFragment_to_postdetailFragment)
        }

    }


}