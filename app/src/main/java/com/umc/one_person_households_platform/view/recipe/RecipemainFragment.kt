package com.umc.one_person_households_platform.view.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.adapter.RecipeListAdapter
import com.umc.one_person_households_platform.databinding.FragmentCommunityBinding
import com.umc.one_person_households_platform.databinding.FragmentRecipemainBinding
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.RecipeDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipemainFragment : Fragment() {


    private lateinit var
            binding: FragmentRecipemainBinding

    lateinit var recipeAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipemain, container, false)

        callApi("최신순")

        binding.rbLatest.setOnClickListener{


            callApi("최신순")
        }
        binding.rbPopular.setOnClickListener{


            callApi("인기순")
        }



        return binding.root
    }


    fun callApi(sort: String) {
        var recipelist: RecipeDTO? = null

        val apiClient = ApiClient.create()

        apiClient.getRecipe(sort).enqueue(object : Callback<RecipeDTO> {
            override fun onResponse(call: Call<RecipeDTO>, response: Response<RecipeDTO>) {
                if (response.isSuccessful) {
                    recipelist = response.body()

                    Log.d("tttt",recipelist.toString())
                    if (recipelist != null) {

                        recipeAdapter = RecipeListAdapter(recipelist!!)
                        binding.rvRecipelist.adapter = recipeAdapter
                        binding.rvRecipelist.layoutManager = LinearLayoutManager(requireContext())

                    }


                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RecipeDTO>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })

    }


}