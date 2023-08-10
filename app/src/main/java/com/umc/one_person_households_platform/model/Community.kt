package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class Community(
    @SerializedName("isSuccess")
    val isSuccess: Boolean, // 요청 성공 여부

    @SerializedName("code")
    val code: Int, // 상태 코드

    @SerializedName("message")
    val message: String, // 요청 결과 메시지

    @SerializedName("result")
    val communityContent: List<CommunityContent>, // 결과 데이터
)

data class CommunityContent(
    @SerializedName("postIdx")
    val postIdx: Int, // 게시물 인덱스

    @SerializedName("categoryIdx")
    val categoryIdx: Int, // 카테고리 인덱스

    @SerializedName("category")
    val category: String, // 카테고리 이름

    @SerializedName("title")
    val title: String, // 게시물 제목

    @SerializedName("nickname")
    val nickname: String, // 사용자 닉네임

    @SerializedName("distance")
    val distance: Int, // 거리

    @SerializedName("createAt")
    val createAt: String, // 생성 시간

    @SerializedName("imagePath")
    val imagePath: String // 이미지 경로
)