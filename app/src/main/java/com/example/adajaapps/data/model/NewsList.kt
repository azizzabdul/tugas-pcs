package com.example.adajaapps.data.model

data class NewsList(
    val title: String = "",
    val version: String = "",
    val href: String = "",
    val results: List<News> = arrayListOf()
)
