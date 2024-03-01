package com.mbappe.radiofrance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mbappe.radiofrance.shows.navigateToShow
import com.mbappe.radiofrance.shows.showsScreen
import com.mbappe.radiofrance.stations.stationsScreen
import com.mbappe.radiofrance.stations.stationsScreenNavigationRoute
import com.mbappe.radiofrance.ui.theme.RadioFranceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            RadioFranceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    NavHost(
                        navController = navController,
                        startDestination = stationsScreenNavigationRoute()
                    ) {
                        stationsScreen(navController::navigateToShow)
                        showsScreen()
                    }
                }
            }
        }
    }
}
