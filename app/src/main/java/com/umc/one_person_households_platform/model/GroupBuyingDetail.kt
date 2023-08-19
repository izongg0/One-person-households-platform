package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class GroupBuyingDetail(
    @SerializedName("isSuccess")
    val isSuccess: String, // 요청 및 응답 성공 여부

    @SerializedName("code")
    val code: Int, // 요청에 대한 응답의 종류

    @SerializedName("message")
    val message: String, // 안내 메시지

    @SerializedName("result")
    val groupBuyingDetailContent: GroupBuyingDetailContent, // 게시글 상세 화면
)

data class GroupBuyingDetailContent(
    @SerializedName("paths")
    val paths: List<String>, // 사진 경로

    @SerializedName("postIdx")
    val postIdx: Int, // 게시글 인덱스

    @SerializedName("categoryIdx")
    val categoryIdx: Int, // 카테고리 인덱스

    @SerializedName("userIdx")
    val userIdx: Int, // 유저 인덱스

    @SerializedName("title")
    val title: String, // 글 제목

    @SerializedName("viewCount")
    val viewCount: Int, // 조회수

    @SerializedName("likeCount")
    val likeCount: Int, // 관심 표시 수

    @SerializedName("createAt")
    val createAt: String, // 작성 시각

    @SerializedName("updateAt")
    val updateAt: String, // 수정 시각

    @SerializedName("url")
    val url: String, // 글의 url

    @SerializedName("groupPurchaseDetailIdx")
    val groupPurchaseDetailIdx: Int, // 게시글 인덱스

    @SerializedName("productName")
    val productName: String, // 상품명

    @SerializedName("productURL")
    val productURL: String, // 상품의 URL

    @SerializedName("singlePrice")
    val singlePrice: Double, // 단일 상품 가격

    @SerializedName("deliveryFee")
    val deliveryFee: Double, // 배송 비용

    @SerializedName("members")
    val members: Int, // 공구 참여자 수

    @SerializedName("deadline")
    val deadline: String, // 공구 마감 기한

    @SerializedName("hasExtension")
    val hasExtension: Boolean, // 마감 연장 여부

    @SerializedName("calculated")
    val calculated: Boolean, // 정산 완료 여부

    @SerializedName("comments")
    val comments: List<Int> // 해당 글의 댓글 idx
)