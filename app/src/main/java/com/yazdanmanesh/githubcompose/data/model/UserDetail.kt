package com.yazdanmanesh.githubcompose.data.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
    @SerializedName("name") val name: String? = null,
    @SerializedName("location") val location: String? = "",
    @SerializedName("blog") val blogUrl: String = "",
    @SerializedName("public_repos") val publicRepos: Int = 0,
    @SerializedName("followers") val followers: Int = 0,
    @SerializedName("following") val following: Int = 0,
)

fun buildUserDetailPreview() = UserDetail(
    avatarUrl = "https://avatars.githubusercontent.com/Amir-yazdanmanesh",
    htmlUrl = "https://github.com/16717834",
    name = "Abhishek Pathak",
    location = "London",
    publicRepos = 50,
    followers = 50,
    following = 0
)
