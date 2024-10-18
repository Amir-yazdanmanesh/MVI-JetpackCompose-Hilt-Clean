package com.yazdanmanesh.githubcompose.data.remote

object Endpoints {
    const val BASE_URL = "https://api.github.com/"

    const val GET_USERS = "users?since=16717834"
    const val GET_USER = "users/{userLogin}"
    const val GET_REPOS_BY_USER = "users/{userLogin}/repos?sort=stars&order=desc"
}
