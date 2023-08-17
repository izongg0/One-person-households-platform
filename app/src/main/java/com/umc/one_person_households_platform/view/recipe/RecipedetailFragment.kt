package com.umc.one_person_households_platform.view.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentPostdetailBinding
import com.umc.one_person_households_platform.databinding.FragmentRecipedetailBinding
import com.umc.one_person_households_platform.model.CommentAddItems
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.model.RecipeDetail
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipedetailFragment : Fragment() {


    private lateinit var
            binding: FragmentRecipedetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipedetail,container,false)


        binding.ivBackarrow.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_recipedetailFragment_to_recipemainFragment)

        }
        val myData = arguments?.getInt("recipe_post")

        var recipedetailcontent: RecipeDetail? = null

        val apiClient = ApiClient.create()

        apiClient.getRecipeDetail(myData!!).enqueue(object : Callback<RecipeDetail> {
            override fun onResponse(
                call: Call<RecipeDetail>,
                response: Response<RecipeDetail>
            ) {
                if (response.isSuccessful) {

                    recipedetailcontent = response.body()

                    binding.tvTitle.text = recipedetailcontent!!.result.title
                    binding.tvTag.text = recipedetailcontent!!.result.tag
                    binding.tvContent.text = recipedetailcontent!!.result.contents




                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RecipeDetail>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })



        return binding.root
    }


}