package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class GroupBuying(
    @SerializedName("isSuccess")
    val isSuccess: String, // 요청 및 응답 성공 여부

    @SerializedName("code")
    val code: Int, // 요청에 대한 응답의 종류

    @SerializedName("message")
    val message: String, // 안내 메시지

    @SerializedName("result")
    val GroupBuyingContent: List<GroupBuyingContent>, // 게시글 리스트
)

data class GroupBuyingContent(
    @SerializedName("postIdx")
    val postIdx: Int, // 게시글 인덱스

    @SerializedName("categoryIdx")
    val categoryIdx: Int, // 카테고리 인덱스

    @SerializedName("category")
    val category: String, // 카테고리 이름

    @SerializedName("title")
    val title: String, // 게시글 제목

    @SerializedName("productName")
    val productName: String, // 상품 이름

    @SerializedName("nickname")
    val nickname: String, // 게시글 작성자 닉네임

    @SerializedName("distance")
    val distance: String, // 게시글 작성자와 사용자 사이의 거리

    @SerializedName("reamainDay")
    val reamainDay: String, // 마감기한까지 남은 일 수

    @SerializedName("imagePath")
    val imagePath: String, // 게시글 미리보기 이미지 주소(게시글에 처음으로 첨부된 이미지)
)