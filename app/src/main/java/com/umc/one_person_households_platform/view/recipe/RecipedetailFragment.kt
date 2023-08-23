package com.umc.one_person_households_platform.view.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentPostdetailBinding
import com.umc.one_person_households_platform.databinding.FragmentRecipedetailBinding
import com.umc.one_person_households_platform.model.CommentAddItems
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.model.RecipeDetail
import com.umc.one_person_households_platform.model.RecipeScrapBody
import com.umc.one_person_households_platform.model.RecipeScrapResponse
import com.umc.one_person_households_platform.network.ApiClient
import com.umc.one_person_households_platform.view.community.PostdetailFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipedetailFragment : Fragment() {


    private lateinit var
            binding: FragmentRecipedetailBinding
    private val arg: RecipedetailFragmentArgs by navArgs()


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
        val myData = arg.recipeIdx

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



                    val format_ingredients = recipedetailcontent!!.result.ingredients.split("|").joinToString("\n")


                    val lines = recipedetailcontent!!.result.description.split("|")
                    val formattedLines = StringBuilder()

                    for ((index, line) in lines.withIndex()) {
                        val lineNumber = index + 1
                        val formattedLine = "$lineNumber. $line\n\n"
                        formattedLines.append(formattedLine)
                    }

                    val finalFormattedText = formattedLines.toString()

                    Glide.with(requireContext()).load(recipedetailcontent!!.result.mainImageUrl).into(binding.ivMainImg)

                    binding.tvDescription.text = format_ingredients
                    binding.tvCook.text = finalFormattedText

                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RecipeDetail>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })

        var current_scrap = arg.isScrap


        if (current_scrap){

            binding.ivScrap.setImageResource(R.drawable.btn_recipe_bookmark_clicked)

        }else{
            binding.ivScrap.setImageResource(R.drawable.btn_recipe_bookmark_non)

        }

        binding.ivScrap.setOnClickListener {

            if (current_scrap){
                current_scrap = false
                binding.ivScrap.setImageResource(R.drawable.btn_recipe_bookmark_non)

                val addscrap = RecipeScrapBody(arg.recipeIdx,4)
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
                            Log.d("yyyyyyy", "성공 결과: ${result.toString()}")
                        } else {
                            val result = response.body()

                            Log.d("yyyyyyy", "성공 실패: ${result.toString()}")

                        }
                    }

                    override fun onFailure(call: Call<RecipeScrapResponse>, t: Throwable) {
                        Log.d("yyyyyyy", "오류 처리: ${t.toString()}")

                    }
                })


            }else{
                binding.ivScrap.setImageResource(R.drawable.btn_recipe_bookmark_clicked)
                current_scrap = true


                val addscrap = RecipeScrapBody(arg.recipeIdx,4)
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

        return binding.root
    }


}