package com.example.booktracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booktracker.ui.viewmodel.BookViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BookDetailScreen(navController: NavController, viewModel: BookViewModel, bookId: Int) {
    val bookWithNotes by viewModel.getBookWithNotes(bookId).collectAsState(initial = null)
    var newNoteContent by remember { mutableStateOf("") }

    bookWithNotes?.let { data ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Información Principal
            Text(text = data.book.title, style = MaterialTheme.typography.headlineLarge)
            Text(text = "Por: ${data.book.author}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data.book.synopsis, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()

            // Sección de Notas
            Text("Notas", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(vertical = 8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(data.notes.sortedBy { it.dateTimestamp }) { note ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = note.content, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(note.dateTimestamp)),
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            // Inserción Rápida
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                TextField(
                    value = newNoteContent,
                    onValueChange = { newNoteContent = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Escribe una nota...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (newNoteContent.isNotBlank()) {
                        viewModel.insertNote(bookId, newNoteContent)
                        newNoteContent = ""
                    }
                }) {
                    Text("Añadir")
                }
            }
        }
    }
}