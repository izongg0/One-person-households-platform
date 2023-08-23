package com.umc.one_person_households_platform.view.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.RecipeListAdapter
import com.umc.one_person_households_platform.adapter.RecipeScrapAdapter
import com.umc.one_person_households_platform.databinding.FragmentRecipemainBinding
import com.umc.one_person_households_platform.databinding.FragmentScrapBinding
import com.umc.one_person_households_platform.model.RecipeDTO
import com.umc.one_person_households_platform.model.RecipeListItems
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScrapFragment : Fragment() {

    private lateinit var
            binding: FragmentScrapBinding

    lateinit var recipeAdapter: RecipeScrapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scrap, container, false)


        callApi("최신순","인기순")

        binding.ivBackbtn.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_scrapFragment_to_recipemainFragment)
        }




        return binding.root
    }


    fun callApi(sort_1: String, sort_2:String) {

        var scraplist :MutableList<RecipeListItems> = mutableListOf()
        var recipelist: RecipeDTO? = null

        val apiClient = ApiClient.create()

        apiClient.getRecipe(sort_1).enqueue(object : Callback<RecipeDTO> {
            override fun onResponse(call: Call<RecipeDTO>, response: Response<RecipeDTO>) {
                if (response.isSuccessful) {
                    recipelist = response.body()

                    Log.d("tttt",recipelist.toString())
                    if (recipelist != null) {

                        for (i in recipelist!!.result){
                            if (i.likeStatus == true){

                                scraplist.add(i)

                                recipeAdapter = RecipeScrapAdapter(scraplist)
                                binding.rvScrapList.adapter = recipeAdapter
                                binding.rvScrapList.layoutManager = LinearLayoutManager(requireContext())
                            }
                        }











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