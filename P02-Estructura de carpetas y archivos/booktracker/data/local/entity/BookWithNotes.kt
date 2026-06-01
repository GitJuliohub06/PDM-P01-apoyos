package com.example.booktracker.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithNotes(
    @Embedded val book: Book,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val notes: List<BookNote>
)