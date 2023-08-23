package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class GroupBuyingWrite(
    @SerializedName("isSuccess")
    val isSuccess: String, // 요청 및 응답 성공 여부

    @SerializedName("code")
    val code: Int, // 요청에 대한 응답의 종류

    @SerializedName("message")
    val message: String, // 안내 메시지

    @SerializedName("result")
    val groupBuyingWriteContent: GroupBuyingWriteContent,
)

data class GroupBuyingWriteContent(
    @SerializedName("postIdx")
    val postIdx: Int, // 게시글 인덱스

    @SerializedName("categoryIdx")
    val categoryIdx: Int, // 카테고리 인덱스

    @SerializedName("category")
    val category: String, // 카테고리 이름

    @SerializedName("userIdx")
    val userIdx: Int, // 유저 인덱스

    @SerializedName("title")
    val title: String, // 제목

    @SerializedName("url")
    val url: String // 게시글 url
)

data class GroupBuyingWritePost(
    @SerializedName("categoryIdx")
    val categoryIdx: Int,

    @SerializedName("userIdx")
    val userIdx: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("productName")
    val productName: String,

    @SerializedName("productURL")
    val productURL: String,

    @SerializedName("singlePrice")
    val singlePrice: Int,

    @SerializedName("deliveryFee")
    val deliveryFee: Int,

    @SerializedName("members")
    val members: Int,

    @SerializedName("deadline")
    val deadline: String,

    @SerializedName("paths")
    val paths: List<String>
)