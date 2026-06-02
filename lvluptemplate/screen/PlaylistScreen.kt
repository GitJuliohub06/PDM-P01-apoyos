package com.example.lvluptemplate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lvluptemplate.components.AddPlaylistCard
import com.example.lvluptemplate.components.CreatePlaylistDialog
import com.example.lvluptemplate.components.MiniPlayerComponent
import com.example.lvluptemplate.components.PlaylistCardComponent
import com.example.lvluptemplate.components.SimpleBottomBar
import com.example.lvluptemplate.ui.viewmodels.LvlUpViewModel

@Composable
fun PlaylistScreen(navController: NavController, viewModel: LvlUpViewModel) {

    var showDialog by remember { mutableStateOf(false) }

    // Obtenemos playlists desde la base de datos
    val playlists by viewModel.allPlaylists.collectAsState()

    Scaffold(
        bottomBar = {
            Column {
                MiniPlayerComponent()
                SimpleBottomBar(navController = navController)            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = "Your Playlists",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    AddPlaylistCard(onClick = { showDialog = true })

                    if(showDialog){
                        CreatePlaylistDialog(
                            onDismiss = { showDialog = false },
                            onPlaylistCreated = { playlistName ->
                                viewModel.createPlaylist(playlistName)
                                showDialog = false
                            }
                        )
                    }
                }

                items(playlists) { playlistWithSongs ->
                    PlaylistCardComponent(playlistWithSongs = playlistWithSongs)
                    { navController.navigate("my_playlist_screen/${playlistWithSongs.playlist.id}") }
                }
            }
        }
    }
}