package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

// 전달

//{
//    "categoryIdx": 11,
//    "userIdx" : 2,
//    "title": "안녕하세요",
//    "contents" : "질문있습니다",
//    "paths": null
//}
data class CommunityAddpostDTO(
    @SerializedName("categoryIdx")
    val categoryIdx: Int,

    @SerializedName("userIdx")
    val userIdx: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("contents")
    val contents: String,

    @SerializedName("paths")
    val paths: MutableList<String>
)

 // 응답
data class ApiResponse(
    @SerializedName("isSuccess")
    val isSuccess: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: AddResult
)

data class AddResult(
    @SerializedName("postIdx")
    val postIdx: Int,
    @SerializedName("categoryIdx")
    val categoryIdx: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("userIdx")
    val userIdx: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)


