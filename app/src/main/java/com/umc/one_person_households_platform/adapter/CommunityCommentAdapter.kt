package com.umc.one_person_households_platform.adapter



import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommentItems
import org.w3c.dom.Text


class CommunityCommentAdapter(var commentitemlist : MutableList<CommentItems>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CustomViewHolder(view)
    }

    private inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {

        return commentitemlist.size ?: 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        var view = holder.itemView
        view.findViewById<TextView>(R.id.tv_author).text = commentitemlist[position].userIdx.toString()
        view.findViewById<TextView>(R.id.tv_commentcontent).text =commentitemlist[position].contents

//        val imageView = view.findViewById<ImageView>(R.id.iv_imagearea) // Replace with your ImageView ID
//
//        val uri = selectedImageUris[position]
//
//        // Load the image into the ImageView
//        Glide.with(view.context)
//            .load(uri)
//            .into(imageView)




    }


}