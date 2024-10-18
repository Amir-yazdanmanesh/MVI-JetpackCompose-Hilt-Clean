package com.yazdanmanesh.githubcompose.data.repository

import com.yazdanmanesh.githubcompose.data.local.GithubDao
import com.yazdanmanesh.githubcompose.data.remote.GithubApi
import com.yazdanmanesh.githubcompose.di.IoDispatcher
import com.yazdanmanesh.githubcompose.domain.mappers.GithubMapper
import com.yazdanmanesh.githubcompose.domain.models.Repo
import com.yazdanmanesh.githubcompose.domain.models.User
import com.yazdanmanesh.githubcompose.domain.models.UserDetails
import com.yazdanmanesh.githubcompose.domain.repositories.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val dao: GithubDao,
    private val mapper: GithubMapper
) : GithubRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return withContext(dispatcher) {
            runCatching {
                val cachedUsers = dao.getUsers()
                if (cachedUsers.isNotEmpty()) {
                    return@runCatching cachedUsers.map(mapper::fromEntityToDomain)
                }

                val response = githubApi.getUsers()
                val users = response.map(mapper::fromDtoToDomain)
                dao.insertUsers(response.map(mapper::fromDtoToEntity))
                users
            }
        }
    }

    override suspend fun getUser(userId: String): Result<UserDetails?> {
        return withContext(dispatcher) {
            runCatching {
                val cachedUser = dao.getUserDetail(userId)
                cachedUser?.let {
                    return@runCatching mapper.fromDetailsEntityToDomain(it)
                }

                val response = githubApi.getUser(userId)
                val user = mapper.fromDtoToDomain(response, userId)
                user?.let {
                    dao.insertUserDetail(mapper.fromDetailToEntity(user))
                }
                user
            }
        }
    }

    override suspend fun getRepos(userId: String): Result<List<Repo>> {
        return withContext(dispatcher) {
            runCatching {
                val cachedUsers = dao.getRepos(userId)
                if (cachedUsers.isNotEmpty()) {
                    return@runCatching cachedUsers.map {
                        mapper.fromRepoEntityToDomain(it, userId)
                    }
                }

                val response = githubApi.getRepos(userId)

                val users = response.map {
                    mapper.fromRepoDtoToDomain(it, userId)
                }
                dao.insertRepos(response.map {
                    mapper.fromRepoDtoToEntity(it, userId)
                })
                users
            }
        }
    }
}
