package com.example.newsfetcher.feature.mainscreen.data

import com.example.newsfetcher.feature.mainscreen.data.model.ArticleRemoteModel
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

fun ArticleRemoteModel.toDoMain() = ArticleModel(
    title = title?: "",
    description = description?: "" ,
    author = author?: "",
    url = url?: "",
    urlToImage = urlToImage?: "",
    publishedAt = publishedAt?: "",
    mark = false
)

