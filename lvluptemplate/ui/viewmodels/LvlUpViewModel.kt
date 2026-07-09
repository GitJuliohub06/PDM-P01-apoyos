package com.example.lvluptemplate.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lvluptemplate.data.local.LvlUpDao
import com.example.lvluptemplate.data.local.entities.*
import com.example.lvluptemplate.resources.DummyData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LvlUpViewModel(private val dao: LvlUpDao) : ViewModel() {

    // Bloque de inicialización: Carga el DummyData si la BD está vacía
    init {
        viewModelScope.launch {
            dao.getAllSongs().firstOrNull()?.let { currentSongs ->
                if (currentSongs.isEmpty()) {
                    // Insertar Géneros
                    DummyData.genres.forEach { dao.insertGenre(it) }
                    // Insertar Canciones
                    DummyData.allSongs.forEach { dao.insertSong(it) }
                    // Insertar Playlists
                    DummyData.playlists.forEach { dao.insertPlaylist(it) }
                    // Relaciones Canción-Playlist
                    DummyData.playlistSongRelations.forEach { dao.insertPlaylistSongCrossRef(it) }
                }
            }
        }
    }

    val allSongs: StateFlow<List<SongEntity>> = dao.getAllSongs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allPlaylists: StateFlow<List<PlaylistWithSongs>> = dao.getAllPlaylistsWithSongs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _searchResults = MutableStateFlow<List<SongEntity>>(emptyList())
    val searchResults: StateFlow<List<SongEntity>> = _searchResults.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isNotBlank()) {
                dao.searchSongs(query).collect { results ->
                    _searchResults.value = results
                }
            } else {
                _searchResults.value = emptyList()
            }
        }
    }

    fun createPlaylist(name: String, description: String = "") {
        viewModelScope.launch {
            dao.insertPlaylist(PlaylistEntity(name = name, description = description))
        }
    }

    fun addSongToPlaylist(playlistId: Long, songId: Long) {
        viewModelScope.launch {
            dao.insertPlaylistSongCrossRef(PlaylistSongCrossRef(playlistId, songId))
        }
    }

    fun getPlaylistDetails(playlistId: Long): Flow<PlaylistWithSongs> {
        return dao.getPlaylistWithSongsById(playlistId)
    }

    fun playSong(songId: Long) {
        viewModelScope.launch {
            dao.incrementPlayCount(songId)
        }
    }
}

class LvlUpViewModelFactory(private val dao: LvlUpDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LvlUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LvlUpViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}