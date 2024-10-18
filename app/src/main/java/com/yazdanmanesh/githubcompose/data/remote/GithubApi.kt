package com.yazdanmanesh.githubcompose.data.remote

import com.yazdanmanesh.githubcompose.data.remote.model.RepoDto
import com.yazdanmanesh.githubcompose.data.remote.model.UserDto
import com.yazdanmanesh.githubcompose.data.remote.model.UserDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET(Endpoints.GET_USERS)
    suspend fun getUsers(): List<UserDto>

    @GET(Endpoints.GET_USER)
    suspend fun getUser(@Path("userLogin") userId: String): UserDetailsDto?

    @GET(Endpoints.GET_REPOS_BY_USER)
    suspend fun getRepos(@Path("userLogin") userId: String): List<RepoDto>
}
