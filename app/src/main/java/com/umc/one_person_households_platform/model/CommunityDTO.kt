package com.umc.one_person_households_platform.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp


// 커뮤니티 게시글 목록 불러오기
data class CommunityDTO(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Result
)

data class Result(
    val items: List<CommunityPost>,
    val isLast: Boolean
)

data class CommunityPost(
    val postIdx: Int,
    val categoryIdx: Int,
    val category: String,
    val title: String,
    val nickname: String,
    val distance: Int,
    val createAt: String,
    val imagePath: String?
)

//data class CommunityPostItems(
//
//    @SerializedName("postIdx")
//    val postIdx: Int,
//
//    @SerializedName("categoryIdx")
//    val categoryIdx: Int,
//
//    @SerializedName("category")
//    val category: String,
//
//    @SerializedName("title")
//    val title: String,
//
//    @SerializedName("nickname")
//    val nickname: String,
//
//    @SerializedName("distance")
//    val distance: Int,
//
//    @SerializedName("createAt")
//    val createAt: String,
//
//    @SerializedName("imagePath")
//    val imagePath: String
//)


// 커뮤니티 게시글 디테일 페이지

data class CommunityDetailDTO(

    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: CommunityDetailContent?
)
data class CommunityDetailContent(
    val paths: List<String>,
    val postIdx: Int,
    val categoryIdx: Int,
    val userIdx: Int,
    val title: String,
    val viewCount: Int,
    val likeCount: Int,
    val createAt: Timestamp,
    val updateAt: Timestamp,
    val url: String?,
    val communityDetailIdx: Int,
    val contents: String
)











