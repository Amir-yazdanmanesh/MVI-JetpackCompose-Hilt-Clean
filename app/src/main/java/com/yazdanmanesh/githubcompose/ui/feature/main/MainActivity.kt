package com.yazdanmanesh.githubcompose.ui.feature.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.yazdanmanesh.githubcompose.ui.feature.common.BroadcastReceiver
import com.yazdanmanesh.githubcompose.ui.feature.common.ConnectionSnackBar
import com.yazdanmanesh.githubcompose.ui.navigation.AppNavigation
import com.yazdanmanesh.githubcompose.ui.theme.GithubComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val connectivityReceiver = BroadcastReceiver{isConnected ->
        setContent{
            GithubComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation()
                    ConnectionSnackBar(isConnected = isConnected)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        setContent {
            GithubComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }
}
