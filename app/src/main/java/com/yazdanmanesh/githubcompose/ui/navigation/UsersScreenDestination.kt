package com.yazdanmanesh.githubcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yazdanmanesh.githubcompose.ui.feature.users.UsersContract
import com.yazdanmanesh.githubcompose.ui.feature.users.UsersViewModel
import com.yazdanmanesh.githubcompose.ui.feature.users.composables.UsersScreen

@Composable
fun UsersScreenDestination(
    navController: NavController,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    UsersScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is UsersContract.Effect.Navigation.ToRepos) {
                navController.navigateToRepos(navigationEffect.userId)
            }
        }
    )
}
