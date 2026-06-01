package com.example.booktracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booktracker.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController, viewModel: BookViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val books by viewModel.books.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_book") }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir libro")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Buscar libros...") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(books) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { navController.navigate("book_detail/${book.id}") },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = book.title, style = MaterialTheme.typography.titleLarge)
                            Text(text = book.author, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}