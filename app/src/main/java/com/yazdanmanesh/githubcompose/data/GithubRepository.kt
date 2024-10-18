package com.yazdanmanesh.githubcompose.data

import com.yazdanmanesh.githubcompose.data.model.Repo
import com.yazdanmanesh.githubcompose.data.model.User
import com.yazdanmanesh.githubcompose.data.model.UserDetail

interface GithubRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(userId: String): Result<UserDetail?>
    suspend fun getRepos(userId: String): Result<List<Repo>>
}
