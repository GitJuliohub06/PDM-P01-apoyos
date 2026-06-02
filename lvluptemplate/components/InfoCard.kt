package com.example.lvluptemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lvluptemplate.data.local.entities.SongEntity

@Composable
fun InfoCard(song: SongEntity, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(180.dp, 240.dp)
            .padding(end = 16.dp)
            .background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF333333))
                    .height(150.dp)
            ){
                AsyncImage(

                    model = song.coverUrl.ifEmpty { "https://cdn-images.dzcdn.net/images/cover/5718f7c81c27e0b2417e2a4c45224f8a/0x1900-000000-80-0-0.jpg" },
                    contentDescription = "Cover de portada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = song.title, color = Color.White, maxLines = 1)
            Text(text = song.artist, color = Color.Gray, maxLines = 1)
        }
    }
}