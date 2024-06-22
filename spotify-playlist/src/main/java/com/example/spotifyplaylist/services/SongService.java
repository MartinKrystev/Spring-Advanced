package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.entities.Song;
import com.example.spotifyplaylist.domain.entities.Style;
import com.example.spotifyplaylist.domain.enums.StyleName;
import com.example.spotifyplaylist.domain.models.AddSongDto;
import com.example.spotifyplaylist.domain.models.SongDto;

import java.util.List;

public interface SongService {
    List<Song> findAllByStyle(Style style);
    List<SongDto> getStyleSongs(StyleName styleName);
    void createSong(AddSongDto addSongDto);
}

