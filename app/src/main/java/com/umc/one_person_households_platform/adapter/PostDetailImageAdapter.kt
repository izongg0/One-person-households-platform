package com.umc.one_person_households_platform.adapter


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.R


class PostDetailImageAdapter(var selectedImageUris : List<String>,var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_detail_image_item, parent, false)
        return CustomViewHolder(view)
    }

    private inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {

        return selectedImageUris.size ?: 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        var view = holder.itemView


        val imageView = view.findViewById<ImageView>(R.id.iv_imageItem) // Replace with your ImageView ID

        var string_image = stringToBitmap(selectedImageUris[position])

        imageView.setImageBitmap(string_image!!)
        // Load the image into the ImageView

    }

    fun stringToBitmap(encodedString: String?): Bitmap? {
        val encodeByte: ByteArray = java.util.Base64.getDecoder().decode(encodedString)
        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    }
}