package com.yazdanmanesh.githubcompose.ui.feature.users

import androidx.lifecycle.viewModelScope
import com.yazdanmanesh.githubcompose.domain.repositories.GithubRepository
import com.yazdanmanesh.githubcompose.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel<UsersContract.Event, UsersContract.State, UsersContract.Effect>() {

    init { getUsers() }

    override fun setInitialState() = UsersContract.State(
        users = emptyList(),
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: UsersContract.Event) {
        when (event) {
            is UsersContract.Event.UserSelection -> setEffect { UsersContract.Effect.Navigation.ToRepos(event.user.userId) }
            is UsersContract.Event.Retry -> getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }

            githubRepository.getUsers()
                .onSuccess { users ->
                    setState { copy(users = users, isLoading = false) }
                    setEffect { UsersContract.Effect.DataWasLoaded }
                }
                .onFailure {
                    setState { copy(isError = true, isLoading = false) }
                }
        }
    }
}
