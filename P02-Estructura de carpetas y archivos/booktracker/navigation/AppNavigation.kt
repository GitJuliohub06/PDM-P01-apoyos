package com.example.booktracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booktracker.ui.screens.AddBookScreen
import com.example.booktracker.ui.screens.BookDetailScreen
import com.example.booktracker.ui.screens.BookListScreen
import com.example.booktracker.ui.viewmodel.BookViewModel

@Composable
fun AppNavigation(viewModel: BookViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "book_list") {
        composable("book_list") {
            BookListScreen(navController, viewModel)
        }
        composable("add_book") {
            AddBookScreen(navController, viewModel)
        }
        composable(
            route = "book_detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            BookDetailScreen(navController, viewModel, bookId)
        }
    }
}