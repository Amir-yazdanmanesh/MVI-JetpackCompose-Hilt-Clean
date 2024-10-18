package com.yazdanmanesh.githubcompose.data

import com.yazdanmanesh.githubcompose.data.local.GithubDao
import com.yazdanmanesh.githubcompose.data.local.entities.RepoEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserDetailsEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserEntity
import com.yazdanmanesh.githubcompose.data.remote.GithubApi
import com.yazdanmanesh.githubcompose.data.remote.model.RepoDto
import com.yazdanmanesh.githubcompose.data.remote.model.UserDetailsDto
import com.yazdanmanesh.githubcompose.data.remote.model.UserDto
import com.yazdanmanesh.githubcompose.data.repository.GithubRepositoryImpl
import com.yazdanmanesh.githubcompose.domain.mappers.GithubMapper
import com.yazdanmanesh.githubcompose.domain.models.Repo
import com.yazdanmanesh.githubcompose.domain.models.User
import com.yazdanmanesh.githubcompose.domain.models.UserDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepositoryTest {
    private lateinit var githubApi: GithubApi
    private lateinit var dao: GithubDao
    private lateinit var mapper: GithubMapper
    private lateinit var repository: GithubRepositoryImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        githubApi = mockk<GithubApi>(relaxed = true)
        dao = mockk<GithubDao>(relaxed = true)
        mapper = mockk<GithubMapper>(relaxed = true)
        repository = GithubRepositoryImpl(githubApi, testDispatcher, dao, mapper)
    }

    @Test
    fun `When getUsers called and users are cached, should return cached users`() = runTest {
        // Given
        val cachedUsers = listOf(UserEntity())
        val mappedUsers = listOf(User())
        coEvery { dao.getUsers() } returns cachedUsers
        coEvery { mapper.fromEntityToDomain(any()) } returnsMany mappedUsers

        // When
        val result = repository.getUsers()

        // Then
        assert(result.isSuccess)
        assert(result.getOrThrow() == mappedUsers)
        coVerify(exactly = 0) { githubApi.getUsers() }
        coVerify(exactly = 1) { dao.getUsers() }
    }

    @Test
    fun `When getUsers called and no users are cached, should call API and cache users`() =
        runTest {
            // Given
            val users = listOf(UserDto())
            val mappedUsers = listOf(User())
            coEvery { dao.getUsers() } returns emptyList()
        coEvery { githubApi.getUsers() } returns users
            coEvery { mapper.fromDtoToDomain(any()) } returnsMany mappedUsers
            coEvery { mapper.fromDtoToEntity(any()) } returns mockk()

        // When
            val result = repository.getUsers()

        // Then
        assert(result.isSuccess)
            assert(result.getOrThrow() == mappedUsers)
        coVerify(exactly = 1) { githubApi.getUsers() }
            coVerify(exactly = 1) { dao.insertUsers(any()) }
        }

    @Test
    fun `When getUser called and user is cached, should return cached user`() = runTest {
        // Given
        val userId = "16717834"
        val cachedUser = UserDetailsEntity(userId)
        val mappedUser = UserDetails(userId)
        coEvery { dao.getUserDetail(userId) } returns cachedUser
        coEvery { mapper.fromDetailsEntityToDomain(any()) } returns mappedUser

        // When
        val result = repository.getUser(userId)

        // Then
        assert(result.isSuccess)
        assert(result.getOrThrow() == mappedUser)
        coVerify(exactly = 0) { githubApi.getUser(userId) }
    }

    @Test
    fun `When getUser called and user is not cached, should call API and cache user`() = runTest {
        // Given
        val userId = "16717834"
        val userDetailsDto = UserDetailsDto()
        val mappedUser = UserDetails(userId)
        coEvery { dao.getUserDetail(userId) } returns null
        coEvery { githubApi.getUser(userId) } returns userDetailsDto
        coEvery { mapper.fromDtoToDomain(any(), any()) } returns mappedUser
        coEvery { mapper.fromDetailToEntity(any()) } returns mockk()

        // When
        val result = repository.getUser(userId)

        // Then
        assert(result.isSuccess)
        assert(result.getOrThrow() == mappedUser)
        coVerify(exactly = 1) { githubApi.getUser(userId) }
        coVerify(exactly = 1) { dao.insertUserDetail(any()) }
    }

    @Test
    fun `When getRepos called and repos are cached, should return cached repos`() = runTest {
        // Given
        val userId = "16717834"
        val id = 16717834L
        val cachedRepos = listOf(RepoEntity(userId = userId))
        val mappedRepos = listOf(Repo(id = id, userId))
        coEvery { dao.getRepos(userId) } returns cachedRepos
        coEvery { mapper.fromRepoEntityToDomain(any(), any()) } returnsMany mappedRepos

        // When
        val result = repository.getRepos(userId)

        // Then
        assert(result.isSuccess)
        assert(result.getOrThrow() == mappedRepos)
        coVerify(exactly = 0) { githubApi.getRepos(userId) }
    }

    @Test
    fun `When getRepos called and no repos are cached, should call API and cache repos`() =
        runTest {
            // Given
            val userId = "16717834"
            val id = 16717834L
            val repos = listOf(RepoDto())
            val mappedRepos = listOf(Repo(id = id, userId = userId))
            coEvery { dao.getRepos(userId) } returns emptyList()
            coEvery { githubApi.getRepos(userId) } returns repos
            coEvery { mapper.fromRepoDtoToDomain(any(), any()) } returnsMany mappedRepos
            coEvery {
                mapper.fromRepoDtoToEntity(
                    any(),
                    any()
                )
            } returns mockk()

            // When
            val result = repository.getRepos(userId)

            // Then
            assert(result.isSuccess)
            assert(result.getOrThrow() == mappedRepos)
        coVerify(exactly = 1) { githubApi.getRepos(userId) }
            coVerify(exactly = 1) { dao.insertRepos(any()) }
    }

    @Test
    fun `Given an exception When getUsers called then returns failure`() = runTest {
        // Given
        coEvery { githubApi.getUsers() } throws Exception("")

        // When
        val result = repository.getUsers()

        // Then
        assert(result.isFailure)
    }

    @Test
    fun `Given an exception When getUser called then returns failure`() = runTest {
        val userId = "16717834"

        coEvery { dao.getUserDetail(userId) } returns null
        coEvery { githubApi.getUser(any()) } throws Exception("Network Error")

        // When
        val result = repository.getUser(userId)

        // Then
        assert(result.isFailure)
        result.onFailure { exception ->
            assert(exception is Exception)
            assert(exception.message == "Network Error")
        }
    }


    @Test
    fun `Given an exception When getRepos called then returns failure`() = runTest {
        // Given
        coEvery { githubApi.getRepos(any()) } throws Exception("")

        // When
        val result = repository.getRepos("16717834")

        // Then
        assert(result.isFailure)
    }
}
