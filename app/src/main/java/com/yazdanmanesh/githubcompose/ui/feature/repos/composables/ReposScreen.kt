package com.yazdanmanesh.githubcompose.ui.feature.repos.composables

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.yazdanmanesh.githubcompose.domain.models.RepoPreview
import com.yazdanmanesh.githubcompose.domain.models.buildUserDetailPreview
import com.yazdanmanesh.githubcompose.ui.base.SIDE_EFFECTS_KEY
import com.yazdanmanesh.githubcompose.ui.feature.common.NetworkError
import com.yazdanmanesh.githubcompose.ui.feature.common.Progress
import com.yazdanmanesh.githubcompose.ui.feature.repos.ReposContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ReposScreen(
    state: ReposContract.State,
    effectFlow: Flow<ReposContract.Effect>?,
    onEventSent: (event: ReposContract.Event) -> Unit,
    onNavigationRequested: (ReposContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                ReposContract.Effect.Navigation.Back -> {
                    onNavigationRequested(ReposContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    Scaffold(
        topBar = { ReposTopBar {
            onEventSent(ReposContract.Event.BackButtonClicked)
        } }
    ) {
        when {
            state.isUserLoading || state.isReposLoading -> Progress()
            state.isError -> NetworkError { onEventSent(ReposContract.Event.Retry) }
            else -> {
                state.user?.let { user ->
                    ReposList(
                        header = { ReposListHeader(userDetail = user) },
                        reposList = state.reposList
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReposScreenSuccessPreview() {
    val repos = List(3) { RepoPreview.repo }
    ReposScreen(
        state = ReposContract.State(
            user = buildUserDetailPreview(),
            reposList = repos,
            isUserLoading = false,
            isReposLoading = false,
            isError = false,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Preview(showBackground = true)
@Composable
fun ReposScreenErrorPreview() {
    ReposScreen(
        state = ReposContract.State(
            user = buildUserDetailPreview(),
            reposList = emptyList(),
            isUserLoading = false,
            isReposLoading = false,
            isError = true,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
