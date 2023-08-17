package com.umc.one_person_households_platform.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp


// 커뮤니티 게시글 목록
data class CommunityDTO(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<CommunityPost>
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



// 커뮤니티 게시글 디테일 페이지
data class CommunityDetailDTO(

    @SerializedName("isSuccess") val isSuccess: String,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: CommunityDetailContent
)
data class CommunityDetailContent(
    @SerializedName("paths") val paths: List<String>,
    @SerializedName("postIdx") val postIdx: Int,
    @SerializedName("categoryIdx") val categoryIdx: Int,
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("title") val title: String,
    @SerializedName("viewCount") val viewCount: Int,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("createAt") val createAt: String,
    @SerializedName("updateAt") val updateAt: String,
    @SerializedName("url") val url: String,
    @SerializedName("communityDetailIdx") val communityDetailIdx: Int,
    @SerializedName("contents") val contents: String,
    @SerializedName("comments") val comments: List<Int>
)

// 댓글 불러오기
data class CommunityComment(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: CommentItems
)

data class CommentItems(
    val commentIdx: Int,
    val postIdx: Int,
    val userIdx: Int,
    val parentCommentIdx: Int,
    val originIdx: Int,
    val likeCount: Int,
    val contents: String,
    @SerializedName("createAt")
    val createdAt: String,
    @SerializedName("updateAt")
    val updatedAt: String,
    val deleted: Boolean
)

// 댓글 작성

data class CommentAddItems(
    val postIdx: Int,
    val userIdx: Int,
    val parentCommentIdx: Int,
    val contents: String
)
data class CommentAddResult(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: Int
)

data class CommentResultData(
    val result: Int
)

//게시글 삭제

data class PostDeleteDTO(
    val postIdx: Int,
    val userIdx: Int

)
data class PostDeleteItems(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: String
)



data class PostHeartDTO(
    val postIdx: Int,
    val userIdx: Int

)
data class PostHeartResult(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: String
)








