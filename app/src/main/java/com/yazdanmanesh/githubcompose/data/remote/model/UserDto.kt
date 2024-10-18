package com.yazdanmanesh.githubcompose.data.remote.model

import com.google.gson.annotations.SerializedName
import com.yazdanmanesh.githubcompose.domain.models.User

data class UserDto(
    @SerializedName("login") val userId: String = "",
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
)
