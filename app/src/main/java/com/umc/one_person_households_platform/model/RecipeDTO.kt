package com.umc.one_person_households_platform.model

import android.content.ClipData

// 레시피 목록 불러오기
data class RecipeDTO(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<RecipeListItems>
)

data class RecipeListItems(
    val postIdx: Int,
    val title: String,
    var likeStatus: Boolean,
    val likeCount: Int,
    val imagePath: String
)
// 스크랩
data class RecipeScrapBody(
    val postIdx: Int,
    val userIdx: Int

)
data class RecipeScrapResponse(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: String
)

// 레시피 디테일
data class RecipeDetail(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: RecipeDetailItems
)

data class RecipeDetailItems(
    val paths: List<String>,
    val postIdx: Int,
    val categoryIdx: Int,
    val userIdx: Int,
    val title: String,
    val viewCount: Int,
    val likeCount: Int,
    val createAt: String,
    val updateAt: String,
    val url: String,
    val recipeDetailIdx: Int,
    val contents: String,
    val tag: String
)
