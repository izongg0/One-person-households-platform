package com.umc.one_person_households_platform.adapter



import android.app.AlertDialog
import android.net.Uri
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.model.CommentItems
import com.umc.one_person_households_platform.model.CommonPostResult
import com.umc.one_person_households_platform.model.PostDeleteDTO
import com.umc.one_person_households_platform.model.PostDeleteItems
import com.umc.one_person_households_platform.model.PostHeartDTO
import com.umc.one_person_households_platform.network.ApiClient
import com.umc.one_person_households_platform.view.community.CommentMorebtnFragment
import com.umc.one_person_households_platform.view.community.PostMorebtnMeFragment
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommunityCommentAdapter(var commentitemlist : MutableList<CommentItems>, private val parentFragmentManager: FragmentManager) :
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
        if (commentitemlist[position].deleted == false) {
            view.findViewById<TextView>(R.id.tv_author).text =
                commentitemlist[position].userIdx.toString()
            view.findViewById<TextView>(R.id.tv_commentcontent).text =
                commentitemlist[position].contents

            view.findViewById<ImageView>(R.id.iv_comment_morebtn).setOnClickListener {


                val orderBottomDialogFragment: CommentMorebtnFragment = CommentMorebtnFragment() {
                    when (it) {
                        0 -> {
                            //댓글 삭제
                            val apiService = ApiClient.create()
                            val call = apiService.deleteComment(commentitemlist[position].commentIdx)

                            call.enqueue(object : Callback<CommonPostResult> {
                                override fun onResponse(
                                    call: Call<CommonPostResult>,
                                    response: Response<CommonPostResult>
                                ) {
                                    if (response.isSuccessful) {
                                        // 성공적으로 응답이 도착한 경우
                                        val result = response.body()
                                        commentitemlist.removeAt(position)

                                        notifyDataSetChanged()
                                        Log.d("rrrrrrr", "댓글 삭제 성공 결과: ${result.toString()}")
                                    } else {

                                    }
                                }

                                override fun onFailure(call: Call<CommonPostResult>, t: Throwable) {
                                    Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                                }
                            })

                        }

                    }

                }

                orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)




            }
        }
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