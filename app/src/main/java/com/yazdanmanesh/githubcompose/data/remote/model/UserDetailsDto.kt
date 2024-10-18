package com.yazdanmanesh.githubcompose.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDetailsDto(
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
    @SerializedName("name") val name: String? = null,
    @SerializedName("location") val location: String? = "",
    @SerializedName("blog") val blogUrl: String = "",
    @SerializedName("public_repos") val publicRepos: Int = 0,
    @SerializedName("followers") val followers: Int = 0,
    @SerializedName("following") val following: Int = 0,
)