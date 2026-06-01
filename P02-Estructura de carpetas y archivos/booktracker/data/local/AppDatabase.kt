package com.example.booktracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booktracker.data.local.dao.BookDao
import com.example.booktracker.data.local.entity.Book
import com.example.booktracker.data.local.entity.BookNote

@Database(entities = [Book::class, BookNote::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "booktracker_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}