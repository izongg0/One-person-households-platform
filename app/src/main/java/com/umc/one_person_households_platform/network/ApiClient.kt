package com.umc.one_person_households_platform.network

import com.umc.one_person_households_platform.model.CommunityDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {

    // 공동 구매 리스트 출력
    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE1NjExMTksImV4cCI6MTY5MzAzMjM0OH0.BRwHtAtHh3evjLYacLLT5FZZNqGOLdubfsNLrSCLcJ8")



    @GET("/app/boards/community")
    fun getCommunity(
        @Query("category") query: String, @Query("limit") limit: Int // 검색어 파라미터
    ): Call<CommunityDTO>


    @GET("/app/boards/community/search")
    fun getCommunitySearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<CommunityDTO>


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