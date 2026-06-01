package com.example.booktracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booktracker.ui.viewmodel.BookViewModel

@Composable
fun AddBookScreen(navController: NavController, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var synopsis by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Añadir Nuevo Libro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = synopsis,
            onValueChange = { synopsis = it },
            label = { Text("Sinopsis") },
            modifier = Modifier.fillMaxWidth().height(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && author.isNotBlank()) {
                    viewModel.insertBook(title, author, synopsis) {
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Libro")
        }
    }
}