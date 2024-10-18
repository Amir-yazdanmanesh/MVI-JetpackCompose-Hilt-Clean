package com.yazdanmanesh.githubcompose.domain.models

data class User(
    val userId: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
)

fun buildUserPreview() = User(
    userId = "16717834",
    avatarUrl = "https://avatars.githubusercontent.com/Amir-yazdanmanesh",
    htmlUrl = "https://github.com/16717834",
)
