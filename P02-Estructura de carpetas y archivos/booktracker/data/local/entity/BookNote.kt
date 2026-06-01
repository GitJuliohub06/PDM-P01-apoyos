package com.example.booktracker.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "book_notes",
    foreignKeys = [
        ForeignKey(
            entity = Book::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookNote(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val bookId: Int,
    val content: String,
    val dateTimestamp: Long
)