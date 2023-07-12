package com.daniil.weather.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.feature.theme.Typography
import com.example.feature.theme.main
import timber.log.Timber

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screens.Main, Screens.Settings)
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(navController.currentBackStackEntry)
    BottomNavigation(backgroundColor = main, modifier = Modifier.height(70.dp)) {
        items.forEach { screen ->
            BottomNavigationItem(selected = screen.route == backStackEntry?.destination?.route,
                onClick = {
                    if (screen.route != backStackEntry?.destination?.route)
                        navController.navigate(route = screen.route)
                },
                icon = {
                    Image(painter = painterResource(screen.icon), contentDescription = null)
                },
                label = {
                    Text(text = screen.title, style = Typography.body2, color = if (screen.route == backStackEntry?.destination?.route) Color.White else Color.Black, )
                }, selectedContentColor = Color.White
            )
        }
    }
}