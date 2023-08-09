package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

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


data class CommunityPostItems(

    @SerializedName("postIdx")
    val postIdx: Int,

    @SerializedName("categoryIdx")
    val categoryIdx: Int,

    @SerializedName("category")
    val category: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("distance")
    val distance: Int,

    @SerializedName("createAt")
    val createAt: String,

    @SerializedName("imagePath")
    val imagePath: String
)












