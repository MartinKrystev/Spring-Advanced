package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.entities.Song;
import com.example.spotifyplaylist.domain.entities.Style;
import com.example.spotifyplaylist.domain.enums.StyleName;
import com.example.spotifyplaylist.domain.models.AddSongDto;
import com.example.spotifyplaylist.domain.models.SongDto;
import com.example.spotifyplaylist.repositories.SongRepository;
import com.example.spotifyplaylist.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    private final StyleRepository styleRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public void createSong(AddSongDto addSongDto) {
        StyleName styleName = StyleName.valueOf(addSongDto.getStyle());
        Style style = this.styleRepository.findByStyleName(styleName).get();

        Song songToAdd = new Song();
        songToAdd.setPerformer(addSongDto.getPerformer());
        songToAdd.setTitle(addSongDto.getTitle());
        songToAdd.setReleaseDate(addSongDto.getReleaseDate());
        songToAdd.setDuration(addSongDto.getDuration());
        songToAdd.setStyle(style);

        this.songRepository.saveAndFlush(songToAdd);
    }

    public List<SongDto> getStyleSongs(StyleName styleName) {
        Style style = this.styleRepository.findByStyleName(styleName).get();

        return this.songRepository.findAllByStyle(style).get()
                .stream()
                .map(SongDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> findAllByStyle(Style style) {
        return this.songRepository.findAllByStyle(style).get();
    }
}

