package com.example.newsapp.data.remote.dto

data class PaginatedResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ArticleDto>
)
