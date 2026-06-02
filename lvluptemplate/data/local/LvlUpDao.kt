package com.example.lvluptemplate.data.local

import androidx.room.*
import com.example.lvluptemplate.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LvlUpDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaylistSongCrossRef(crossRef: PlaylistSongCrossRef)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<SongEntity>>

    // Búsqueda en múltiples campos requerida en la Segunda Parte
    @Query("""
        SELECT * FROM songs 
        WHERE title LIKE '%' || :query || '%' 
        OR artist LIKE '%' || :query || '%' 
        OR album LIKE '%' || :query || '%'
    """)
    fun searchSongs(query: String): Flow<List<SongEntity>>

    @Transaction
    @Query("SELECT * FROM playlists")
    fun getAllPlaylistsWithSongs(): Flow<List<PlaylistWithSongs>>

    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlaylistWithSongsById(playlistId: Long): Flow<PlaylistWithSongs>

    // Puntos Extra: LvIUP Experience (incrementar contador)
    @Query("UPDATE songs SET playCount = playCount + 1 WHERE id = :songId")
    suspend fun incrementPlayCount(songId: Long)
}