package com.example.booktracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.booktracker.data.local.entity.Book
import com.example.booktracker.data.local.entity.BookNote
import com.example.booktracker.data.local.entity.BookWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: BookNote)

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<Book>>

    @Transaction
    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookWithNotes(bookId: Int): Flow<BookWithNotes>
}