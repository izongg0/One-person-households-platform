package com.umc.one_person_households_platform.network

import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.GroupBuying
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {

    // 공동 구매 리스트 출력
    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE1Nzg2MjUsImV4cCI6MTY5MzA0OTg1NH0.PTnyiTpTf3vV-t9l_T63HYQC9fISO-C8COR8IkISgZY")
    @GET("/app/boards/community")
    fun getCommunity(
        @Query("category") query: String, @Query("limit") limit: Int // 검색어 파라미터
    ): Call<CommunityDTO>

    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE1Nzg2MjUsImV4cCI6MTY5MzA0OTg1NH0.PTnyiTpTf3vV-t9l_T63HYQC9fISO-C8COR8IkISgZY")
    @GET("/app/boards/community/search")
    fun getCommunitySearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<CommunityDTO>

    // 홈 화면 마감 임박 공구 출력
    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4Ijo0LCJpYXQiOjE2OTE1ODA1OTAsImV4cCI6MTY5MzA1MTgxOX0.u0SZtEBBx5UqT3wTSPLgJDuY6OWd8E_FoNMRxmLEWcQ")
    @GET("app/home/grouppurchase")
    suspend fun getGroupBuyingCategories(): Response<GroupBuying>

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