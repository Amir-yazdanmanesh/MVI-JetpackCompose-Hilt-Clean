package com.yazdanmanesh.githubcompose.domain.repositories

import com.yazdanmanesh.githubcompose.domain.models.Repo
import com.yazdanmanesh.githubcompose.domain.models.UserDetails

interface GithubRepository {
    suspend fun getUsers(): Result<List<com.yazdanmanesh.githubcompose.domain.models.User>>
    suspend fun getUser(userId: String): Result<UserDetails?>
    suspend fun getRepos(userId: String): Result<List<Repo>>
}
