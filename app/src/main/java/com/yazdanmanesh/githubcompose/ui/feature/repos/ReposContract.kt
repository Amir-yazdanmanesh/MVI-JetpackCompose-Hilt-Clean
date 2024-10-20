package com.yazdanmanesh.githubcompose.ui.feature.repos

import com.yazdanmanesh.githubcompose.domain.models.Repo
import com.yazdanmanesh.githubcompose.domain.models.UserDetails
import com.yazdanmanesh.githubcompose.ui.base.ViewEvent
import com.yazdanmanesh.githubcompose.ui.base.ViewSideEffect
import com.yazdanmanesh.githubcompose.ui.base.ViewState

class ReposContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        object BackButtonClicked : Event()
    }

    data class State(
        val user: UserDetails?,
        val reposList: List<Repo>,
        val isUserLoading: Boolean,
        val isReposLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }
}
