package com.example.lvluptemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lvluptemplate.screen.*
import com.example.lvluptemplate.ui.viewmodels.LvlUpViewModel

@Composable
fun AppNavigation(viewModel: LvlUpViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {

        composable("main_screen") {
            MainScreen(navController, viewModel)
        }

        composable("search_screen") {
            SearchScreen(navController, viewModel)
        }

        composable("playlist_screen") {
            PlaylistScreen(navController, viewModel)
        }

        composable(
            route = "song_screen/{songId}",
            arguments = listOf(navArgument("songId") { type = NavType.LongType })
        ) { backStackEntry ->
            val songId = backStackEntry.arguments?.getLong("songId") ?: 0L
            SongScreen(navController, viewModel, songId)
        }

        composable(
            route = "my_playlist_screen/{playlistId}",
            arguments = listOf(navArgument("playlistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getLong("playlistId") ?: 0L
            MyPlaylistScreen(navController, viewModel, playlistId)
        }
    }
}