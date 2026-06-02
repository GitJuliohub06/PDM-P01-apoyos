package com.example.lvluptemplate.resources

import com.example.lvluptemplate.data.local.entities.GenreEntity
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.entities.SongEntity

object DummyData {
    val genres = listOf(
        GenreEntity(1L, "R&B / Soul", "Vibras suaves y voces profundas"),
        GenreEntity(2L, "Urbano Latino", "Reggaeton, Trap y el sazón del Caribe"),
        GenreEntity(3L, "Pop / Pop-Rock", "Los éxitos que definieron y definen generaciones"),
        GenreEntity(4L, "Electrónica / Dance", "Beats sintéticos para mover el esqueleto"),
        GenreEntity(5L, "Indie R&B / Lo-Fi", "Música melancólica para viajar de noche")
    )

    val allSongs = listOf(
        SongEntity(1L, "Blamegame", "NSQK", "ATP", "https://m.media-amazon.com/images/I/41ACQgvmvHL._UXNaN_FMjpg_QL85_.jpg", 5L),
        SongEntity(2L, "309", "NSQK", "Bótanica", "https://i.scdn.co/image/ab67616d0000b273db0bf320fc5001cd184af8fe", 5L),
        SongEntity(3L, "LIKE I NEED U", "Keshi", "skeletons", "https://cdn-images.dzcdn.net/images/cover/c93a4c15000ea66fe1e8418640923dc3/0x1900-000000-80-0-0.jpg", 5L),
        SongEntity(4L, "SOMEBODY", "Keshi", "GABRIEL", "https://i.scdn.co/image/ab67616d0000b27319aff2da63b211d75341e8eb", 5L),
        SongEntity(5L, "Out of Time", "The Weeknd", "Dawn FM", "https://cdn-images.dzcdn.net/images/cover/478a544d29275755b3b8f7b4a1fd7a3c/0x1900-000000-80-0-0.jpg", 3L),
        SongEntity(6L, "Best Friends", "The Weeknd", "Dawn FM", "https://cdn-images.dzcdn.net/images/cover/478a544d29275755b3b8f7b4a1fd7a3c/0x1900-000000-80-0-0.jpg", 3L),
        SongEntity(7L, "Aguacero", "Bad Bunny", "Un Verano Sin Ti", "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72", 2L),
        SongEntity(8L, "Tití Me Preguntó", "Bad Bunny", "Un Verano Sin Ti", "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72", 2L),
        SongEntity(9L, "Something About Us", "Daft Punk", "Discovery", "https://cdn-images.dzcdn.net/images/cover/5718f7c81c27e0b2417e2a4c45224f8a/0x1900-000000-80-0-0.jpg", 4L),
        SongEntity(10L, "Get Lucky", "Daft Punk", "Random Access Memories", "https://i.scdn.co/image/ab67616d0000b2739b9b36b0e22870b9f542d937", 4L),
        SongEntity(11L, "Like I Want You", "GIVEON", "Take Time", "https://i.scdn.co/image/ab67616d0000b27390fb297f6a608911e7aaf760", 1L),
        SongEntity(12L, "Unholy Matrimony", "GIVEON", "Give Or Take", "https://i.scdn.co/image/ab67616d00001e02199a5a0f6e5c2c0ab5f478e5", 1L),
        SongEntity(13L, "24K Magic", "Bruno Mars", "24K Magic", "https://i.scdn.co/image/ab67616d0000b273232711f7d66a1e19e89e28c5", 3L),
        SongEntity(14L, "Risk It All", "Bruno Mars", "The Romantic", "https://i.scdn.co/image/ab67616d0000b2733eb8dc748f7efb1470f74395", 3L),
        SongEntity(15L, "GANTEL y BELLAKz", "Omar Courtz", "Por Si Mañana No Estoy", "https://i.scdn.co/image/ab67616d0000b27390af5246adcaa93acb721c17", 2L),
        SongEntity(16L, "KOKO", "Omar Courtz", "Por Si Mañana No Estoy", "https://i.scdn.co/image/ab67616d0000b27390af5246adcaa93acb721c17", 2L),
        SongEntity(17L, "Chicago", "Michael Jackson", "XSCAPE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk-uggu1z71LCKj0q_OuLwNI_7uYCky_2ETw&s", 3L),
        SongEntity(18L, "Remember the Time", "Michael Jackson", "Dangerous", "https://cdn-images.dzcdn.net/images/cover/93a5354699d552666448e1c87c976605/1900x1900-000000-80-0-0.jpg", 3L)
    )

    val playlists = listOf(
        PlaylistEntity(1L, "Late Night Vibes", "Para cuando el mood está melancólico y relajado."),
        PlaylistEntity(2L, "Party Starters 🔥", "Éxitos para encender cualquier lugar."),
        PlaylistEntity(3L, "The Kings of Pop", "De Michael Jackson a The Weeknd, pura realeza musical."),
        PlaylistEntity(4L, "Favorites", "Canciones que te gustan")
    )

    val playlistSongRelations = listOf(
        PlaylistSongCrossRef(1L, 1L), PlaylistSongCrossRef(1L, 3L), PlaylistSongCrossRef(1L, 4L), PlaylistSongCrossRef(1L, 11L),
        PlaylistSongCrossRef(2L, 5L), PlaylistSongCrossRef(2L, 8L), PlaylistSongCrossRef(2L, 10L), PlaylistSongCrossRef(2L, 13L), PlaylistSongCrossRef(2L, 15L),
        PlaylistSongCrossRef(3L, 17L), PlaylistSongCrossRef(3L, 18L), PlaylistSongCrossRef(3L, 5L), PlaylistSongCrossRef(3L, 14L)
    )
}