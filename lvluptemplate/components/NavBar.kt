package com.example.lvluptemplate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun SimpleBottomBar(navController: NavController, currentSelection: Int = 0) {
    var selectedIndex by rememberSaveable { mutableStateOf(currentSelection) }
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.List
    )

    NavigationBar(
        containerColor = Color(0xFF0F0F0F)
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    when(index) {
                        0 -> navController.navigate("main_screen") { launchSingleTop = true }
                        1 -> navController.navigate("search_screen") { launchSingleTop = true }
                        2 -> navController.navigate("playlist_screen") { launchSingleTop = true }
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF7E49C3),
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}