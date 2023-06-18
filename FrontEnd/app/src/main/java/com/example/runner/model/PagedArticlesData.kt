package com.example.runner.model

data class PagedArticlesData(
    val page: Int,
    val pageSize: Int,
    val total: Int,
    val pages: Int,
    val articles: MutableList<Article>
)
