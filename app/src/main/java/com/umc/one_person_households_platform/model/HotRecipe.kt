package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class HotRecipe(
    @SerializedName("isSuccess")
    val isSuccess: String, // 요청 성공 여부

    @SerializedName("code")
    val code: Int, // 상태 코드

    @SerializedName("message")
    val message: String, // 요청 결과 메시지

    @SerializedName("result")
    val hotRecipeContent: List<HotRecipeContent> // 결과 데이터
)

data class HotRecipeContent(
    @SerializedName("postIdx")
    val postIdx: Int, // 게시글 인덱스

    @SerializedName("title")
    val title: String, // 게시글 제목

    @SerializedName("likeStatus")
    val likeStatus: Boolean, // 스크랩 유무

    @SerializedName("LikeCount")
    val likeCount: Int, // 스크랩 수

    @SerializedName("imagePath")
    val imagePath: String// 게시글 미리 보기 이미지 주소
)