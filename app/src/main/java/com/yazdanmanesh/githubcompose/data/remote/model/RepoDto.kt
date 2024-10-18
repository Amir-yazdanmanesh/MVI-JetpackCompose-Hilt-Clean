package com.yazdanmanesh.githubcompose.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepoDto(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String? = null,
    @SerializedName("watchers_count") val watchersCount: Int = 0,
    @SerializedName("forks_count") val forksCount: Int = 0,
    @SerializedName("stargazers_count") val stargazersCount: Int = 0,
    @SerializedName("language") val language: String? = null,
    @SerializedName("html_url") val htmlUrl: String = "",
)
