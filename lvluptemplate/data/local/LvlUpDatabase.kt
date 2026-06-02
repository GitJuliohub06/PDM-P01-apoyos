package com.example.lvluptemplate.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lvluptemplate.data.local.entities.*

@Database(
    entities = [
        GenreEntity::class,
        SongEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LvlUpDatabase : RoomDatabase() {
    abstract fun lvlUpDao(): LvlUpDao

    companion object {
        @Volatile
        private var INSTANCE: LvlUpDatabase? = null

        fun getDatabase(context: Context): LvlUpDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LvlUpDatabase::class.java,
                    "lvlup_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}