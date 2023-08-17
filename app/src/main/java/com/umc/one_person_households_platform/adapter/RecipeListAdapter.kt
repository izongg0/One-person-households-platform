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
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.model.RecipeDTO
import com.umc.one_person_households_platform.model.RecipeScrapBody
import com.umc.one_person_households_platform.model.RecipeScrapResponse
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListAdapter(var recipelist: RecipeDTO) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)


        return CustomViewHolder(view)
    }

    private inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {

        return recipelist?.result?.size ?: 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        var view = holder.itemView


        view.findViewById<TextView>(R.id.tv_recipetitle).text =
            recipelist!!.result[position].title
        view.findViewById<TextView>(R.id.tv_scrapcount).text =
            recipelist!!.result[position].likeCount.toString()


        view.findViewById<ImageView>(R.id.iv_scrap).setOnClickListener {

//            val apiService = ApiClient.create()
//            val call = apiService.addCommunityPost(postadd)
//
//            call.enqueue(object : Callback<ApiResponse> {
//                override fun onResponse(
//                    call: Call<ApiResponse>,
//                    response: Response<ApiResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        // 성공적으로 응답이 도착한 경우
//                        val result = response.body()
//                        Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
//                    } else {
//
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                    Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
//                }
//            })

        }

        if(recipelist!!.result[position].likeStatus){

            view.findViewById<ImageView>(R.id.iv_scrap).setImageResource(R.drawable.btn_recipe_bookmark_clicked)
        }else{

            view.findViewById<ImageView>(R.id.iv_scrap).setImageResource(R.drawable.btn_recipe_bookmark_non)

        }
        var scrapcount = recipelist!!.result[position].likeCount

        view.findViewById<ImageView>(R.id.iv_scrap).setOnClickListener {


            if (recipelist!!.result[position].likeStatus){
                recipelist!!.result[position].likeStatus = false
                view.findViewById<ImageView>(R.id.iv_scrap).setImageResource(R.drawable.btn_recipe_bookmark_non)

                scrapcount = scrapcount -1
                view.findViewById<TextView>(R.id.tv_scrapcount).text =
                    scrapcount.toString()

                val addscrap = RecipeScrapBody(recipelist!!.result[position].postIdx,3)
                val apiService = ApiClient.create()
                val call = apiService.cancelRecipeBookmark(addscrap)

                call.enqueue(object : Callback<RecipeScrapResponse> {
                    override fun onResponse(
                        call: Call<RecipeScrapResponse>,
                        response: Response<RecipeScrapResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 성공적으로 응답이 도착한 경우
                            val result = response.body()
                            Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
                        } else {

                        }
                    }

                    override fun onFailure(call: Call<RecipeScrapResponse>, t: Throwable) {
                        Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                    }
                })


            }else{
                view.findViewById<ImageView>(R.id.iv_scrap).setImageResource(R.drawable.btn_recipe_bookmark_clicked)
                recipelist!!.result[position].likeStatus = true

                scrapcount = scrapcount + 1

                view.findViewById<TextView>(R.id.tv_scrapcount).text =
                    scrapcount.toString()

                val addscrap = RecipeScrapBody(recipelist!!.result[position].postIdx,3)
                val apiService = ApiClient.create()
                val call = apiService.addRecipeBookmark(addscrap)

                call.enqueue(object : Callback<RecipeScrapResponse> {
                    override fun onResponse(
                        call: Call<RecipeScrapResponse>,
                        response: Response<RecipeScrapResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 성공적으로 응답이 도착한 경우
                            val result = response.body()
                            Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
                        } else {

                        }
                    }

                    override fun onFailure(call: Call<RecipeScrapResponse>, t: Throwable) {
                        Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                    }
                })
            }

        }




        view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.cl_click).setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("recipe_post",recipelist!!.result[position].postIdx ) // 데이터 추가

            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_recipemainFragment_to_recipedetailFragment, bundle)

        }


//        Glide.with(view.context)
//            .load(recipelist!!.result.items[position].imagePath)
//            .into(view.findViewById<ImageView>(R.id.iv_recipeimg))

//        view.findViewById<TextView>(R.id.tv_posttitle).setOnClickListener {
//
//            val bundle = Bundle()
//            bundle.putInt("this_post",recipelist!!.result.items[position].postIdx ) // 데이터 추가
//
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_communityFragment_to_postdetailFragment, bundle)
//
//        }
    }
}