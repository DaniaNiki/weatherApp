package com.daniil.weather.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.feature.theme.Typography
import com.example.feature.theme.main
import timber.log.Timber

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screens.Main, Screens.Settings)
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(navController.currentBackStackEntry)
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(Color.Transparent), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        items.forEach { screen ->
            Spacer(modifier = Modifier.padding(start = 10.dp))
            AddItem(screen = screen, currentDestination = currentDestination , navController = navController)
        }
    }
}