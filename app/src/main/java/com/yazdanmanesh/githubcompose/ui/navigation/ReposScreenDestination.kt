package com.yazdanmanesh.githubcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yazdanmanesh.githubcompose.ui.feature.repos.ReposContract
import com.yazdanmanesh.githubcompose.ui.feature.repos.ReposViewModel
import com.yazdanmanesh.githubcompose.ui.feature.repos.composables.ReposScreen


@Composable
fun ReposScreenDestination(
    userId: String,
    navController: NavController,
    viewModel: ReposViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = userId) {
        viewModel.init(userId)
    }

    ReposScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ReposContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
