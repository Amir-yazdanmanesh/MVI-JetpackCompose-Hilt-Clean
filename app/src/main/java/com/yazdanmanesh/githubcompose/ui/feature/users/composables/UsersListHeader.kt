package com.yazdanmanesh.githubcompose.ui.feature.users.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yazdanmanesh.githubcompose.R
import com.yazdanmanesh.githubcompose.ui.theme.OnSurfaceBackgroundAlpha

@Composable
fun UsersListHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSurface.copy(alpha = OnSurfaceBackgroundAlpha))
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.users_list_header),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UsersListHeaderPreview() {
    UsersListHeader()
}