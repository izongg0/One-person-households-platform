package com.umc.one_person_households_platform.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityDetailDTO
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

        return realpostlist?.result?.size ?: 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        var view = holder.itemView


        view.findViewById<TextView>(R.id.tv_posttitle).text =
            realpostlist!!.result[position].title

        view.findViewById<TextView>(R.id.tv_author).text =
            realpostlist!!.result[position].nickname
        view.findViewById<TextView>(R.id.tv_category).text =
            realpostlist!!.result[position].category
        view.findViewById<TextView>(R.id.tv_distance).text =
            realpostlist!!.result[position].distance.toString()
        view.findViewById<TextView>(R.id.tv_time).text =
            realpostlist!!.result[position].createAt


        Glide.with(view.context)
            .load(realpostlist!!.result[position].imagePath)
            .into(view.findViewById<ImageView>(R.id.iv_headimg))

        view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.cl_postclick).setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("this_post",realpostlist!!.result[position].postIdx ) // 데이터 추가

            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_communityFragment_to_postdetailFragment, bundle)

        }
    }
}