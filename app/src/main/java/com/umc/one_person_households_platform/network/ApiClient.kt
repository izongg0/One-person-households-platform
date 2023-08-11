package com.umc.one_person_households_platform.network

import com.umc.one_person_households_platform.BuildConfig
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommunityAddpostDTO
import com.umc.one_person_households_platform.model.Community
import com.umc.one_person_households_platform.model.CommunityDTO
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.model.GroupBuying
import com.umc.one_person_households_platform.model.HotRecipe
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    // 공동 구매 리스트 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/community")
    fun getCommunity(
        @Query("category") query: String, @Query("limit") limit: Int // 검색어 파라미터
    ): Call<CommunityDTO>

    // 커뮤니티 게시글 검색
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/community/search")
    fun getCommunitySearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<CommunityDTO>

    // 커뮤니티 게시글 디테일 화면
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/post/get")
    fun getPostDetail(@Body postIdx: Int): Call<CommunityDetailDTO>
//    @Headers("X-ACCESS-TOKEN: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoyLCJpYXQiOjE2OTE1Nzg2MjUsImV4cCI6MTY5MzA0OTg1NH0.PTnyiTpTf3vV-t9l_T63HYQC9fISO-C8COR8IkISgZY")
//    @GET("/app/post/get")
//    fun getPostDetail(@Body postIdx: Int): Call<CommunityDetailDTO>

    // 홈 화면 마감 임박 공구 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/grouppurchase")
    suspend fun getGroupBuyingCategories(): Response<GroupBuying>

    // 홈 화면 커뮤니티 인기글 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/community")
    suspend fun getCommunityCategories(): Response<Community>

    // 홈 화면 금주 HOT 레시피 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/recipe")
    suspend fun getHotRecipeCategory(): Response<HotRecipe>

    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/create")
    fun addCommunityPost(@Body postData: CommunityAddpostDTO): Call<ApiResponse>

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