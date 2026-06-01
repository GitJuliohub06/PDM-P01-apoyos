package com.example.booktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.booktracker.data.local.AppDatabase
import com.example.booktracker.data.repository.BookRepository
import com.example.booktracker.navigation.AppNavigation
import com.example.booktracker.ui.theme.BooktrackerTheme
import com.example.booktracker.ui.viewmodel.BookViewModel
import com.example.booktracker.ui.viewmodel.BookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización manual de dependencias (Si no usas Hilt/Dagger)
        val database = AppDatabase.getDatabase(this)
        val repository = BookRepository(database.bookDao())
        val factory = BookViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]

        setContent {
            BooktrackerTheme() { // Asegúrate de que el nombre del tema coincida con el generado por Android Studio
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}