package com.yazdanmanesh.githubcompose.data

import com.yazdanmanesh.githubcompose.data.model.Repo
import com.yazdanmanesh.githubcompose.data.model.User
import com.yazdanmanesh.githubcompose.data.model.UserDetail
import com.yazdanmanesh.githubcompose.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GithubRepository {

    override suspend fun getUsers(): Result<List<User>> = makeApiCall(dispatcher) {
        githubApi.getUsers()
    }

    override suspend fun getUser(userId: String): Result<UserDetail?> = makeApiCall(dispatcher) {
        githubApi.getUser(userId)
    }

    override suspend fun getRepos(userId: String): Result<List<Repo>> = makeApiCall(dispatcher) {
        githubApi.getRepos(userId)
    }

}

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        call.invoke()
    }
}
