package com.yazdanmanesh.githubcompose.domain.models

data class UserDetails(
    val id: String,
    val avatarUrl: String = "",
    val htmlUrl: String = "",
    val name: String? = null,
    val location: String? = "",
    val blogUrl: String = "",
    val publicRepos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
)

fun buildUserDetailPreview() = UserDetails(
    avatarUrl = "https://avatars.githubusercontent.com/Amir-yazdanmanesh",
    htmlUrl = "https://github.com/16717834",
    name = "Abhishek Pathak",
    location = "London",
    publicRepos = 50,
    followers = 50,
    following = 0,
    id = ""
)
