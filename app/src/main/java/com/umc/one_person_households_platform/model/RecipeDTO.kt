package com.umc.one_person_households_platform.model

import android.content.ClipData

// 레시피 목록 불러오기
data class RecipeDTO(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: RecipeListResult
)

data class RecipeListResult(
    val items: List<RecipeListItems>,
    val isLast: Boolean
)

data class RecipeListItems(
    val postIdx: Int,
    val title: String,
    val likeStatus: Boolean,
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
