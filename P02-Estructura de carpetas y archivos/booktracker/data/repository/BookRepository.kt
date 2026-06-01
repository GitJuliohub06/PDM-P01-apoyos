package com.example.booktracker.data.repository

import com.example.booktracker.data.local.dao.BookDao
import com.example.booktracker.data.local.entity.Book
import com.example.booktracker.data.local.entity.BookNote
import com.example.booktracker.data.local.entity.BookWithNotes
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    suspend fun insertBook(book: Book) = bookDao.insertBook(book)

    suspend fun insertNote(note: BookNote) = bookDao.insertNote(note)

    fun searchBooks(query: String): Flow<List<Book>> = bookDao.searchBooks(query)

    fun getBookWithNotes(bookId: Int): Flow<BookWithNotes> = bookDao.getBookWithNotes(bookId)
}