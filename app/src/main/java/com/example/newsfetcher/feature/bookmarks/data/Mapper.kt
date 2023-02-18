package com.example.newsfetcher.feature.bookmarks.data

import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
    title = title,
    description = description,
    url = urlToImage,
    author = author,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    mark = true
    )

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title,
    description = description,
    url = urlToImage,
    author = author,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)

