package com.yazdanmanesh.githubcompose.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val userId: String = "",
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
)

fun buildUserPreview() = User(
    userId = "16717834",
    avatarUrl = "https://avatars.githubusercontent.com/Amir-yazdanmanesh",
    htmlUrl = "https://github.com/16717834",
)
