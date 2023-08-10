package com.umc.one_person_households_platform.network

import com.umc.one_person_households_platform.model.AddResult
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommunityAddpostDTO
import com.umc.one_person_households_platform.model.CommunityDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    // 공동 구매 리스트 출력
    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE2NTI5OTksImV4cCI6MTY5MzEyNDIyOH0.euC4O_F_PnQ5i0Q0G7Ukzssdkv2gHQQVEeX8lcDuo3s")
    @GET("/app/boards/community")
    fun getCommunity(
        @Query("category") query: String, @Query("limit") limit: Int // 검색어 파라미터
    ): Call<CommunityDTO>

    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE2NTI5OTksImV4cCI6MTY5MzEyNDIyOH0.euC4O_F_PnQ5i0Q0G7Ukzssdkv2gHQQVEeX8lcDuo3s")    @GET("/app/boards/community/search")
    fun getCommunitySearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<CommunityDTO>


    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE2NTI5OTksImV4cCI6MTY5MzEyNDIyOH0.euC4O_F_PnQ5i0Q0G7Ukzssdkv2gHQQVEeX8lcDuo3s")
    @POST("/app/post/create"
    )
    fun addCommunityPost(@Body postData: CommunityAddpostDTO): Call<ApiResponse>

//    {
//        "categoryIdx": "11",
//        "userIdx" : 2,
//        "title": "안녕하세요",
//        "contents" : "질문있습니다",
//        "paths": []
//    }
    companion object {

        private const val baseUrl = "https://www.jachsang.shop/"

        fun create(): ApiClient {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}