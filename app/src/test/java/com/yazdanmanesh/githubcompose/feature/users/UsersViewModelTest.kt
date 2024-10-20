package com.yazdanmanesh.githubcompose.feature.users

import androidx.compose.runtime.snapshots.Snapshot
import com.yazdanmanesh.githubcompose.utils.MainCoroutineRule
import com.yazdanmanesh.githubcompose.domain.models.User
import com.yazdanmanesh.githubcompose.domain.repositories.GithubRepository
import com.yazdanmanesh.githubcompose.ui.feature.users.UsersContract
import com.yazdanmanesh.githubcompose.ui.feature.users.UsersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@DelicateCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val githubRepository = mockk<GithubRepository>()

    @Test
    fun `When view model initialized then should emit initial view state first`() = runTest {
        // Given
        val expectedInitialViewState = UsersContract.State(
            users = emptyList(),
            isLoading = true,
            isError = false
        )

        // When
        val viewModel = UsersViewModel(githubRepository)

        // Then
        assertEquals(expectedInitialViewState, viewModel.viewState.value)
    }

    @Test
    fun `When getUsers called then should emit a view state`() = runTest {
        // Given
        val users = listOf(User(userId = "16717834"))
        val expectedViewState = UsersContract.State(
            users = users,
            isLoading = false,
            isError = false
        )
        coEvery { githubRepository.getUsers() } returns Result.success(users)

        // When
        val viewModel = UsersViewModel(githubRepository)

        // Then
        assertEquals(expectedViewState, viewModel.viewState.value)
    }

    @Test
    fun `When `() = runTest {

        // Given
        val users = listOf(User(userId = "16717834"))
        val expectedViewState = UsersContract.State(
            users = users,
            isLoading = false,
            isError = false
        )
        coEvery { githubRepository.getUsers() } returns Result.success(users)

        // When
        val snapshot = Snapshot.takeMutableSnapshot(
            writeObserver = {
                println(it)
            }
        )

        snapshot.enter {
            val viewModel = UsersViewModel(githubRepository)

            // Then
            assertEquals(expectedViewState, viewModel.viewState.value)
        }

        snapshot.apply()
    }

}