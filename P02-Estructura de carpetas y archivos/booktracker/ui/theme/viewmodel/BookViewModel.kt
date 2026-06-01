package com.example.booktracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.booktracker.data.local.entity.Book
import com.example.booktracker.data.local.entity.BookNote
import com.example.booktracker.data.local.entity.BookWithNotes
import com.example.booktracker.data.repository.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    val books: Flow<List<Book>> = _searchQuery.flatMapLatest { query ->
        repository.searchBooks(query)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun insertBook(title: String, author: String, synopsis: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.insertBook(Book(title = title, author = author, synopsis = synopsis))
            onSuccess()
        }
    }

    fun getBookWithNotes(bookId: Int): Flow<BookWithNotes> {
        return repository.getBookWithNotes(bookId)
    }

    fun insertNote(bookId: Int, content: String) {
        viewModelScope.launch {
            val note = BookNote(
                bookId = bookId,
                content = content,
                dateTimestamp = System.currentTimeMillis()
            )
            repository.insertNote(note)
        }
    }
}

// Factory necesario para inyectar el Repositorio en el ViewModel
class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}