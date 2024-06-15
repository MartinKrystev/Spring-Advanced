package com.example.spotifyplaylist.domain.models;

import com.example.spotifyplaylist.domain.entities.Song;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private Long id;
    private String performer;
    private String title;
    private int duration;

    public SongDto(Song song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d min)", performer, title, duration);
    }

    public String formatTime() {
        int min = this.duration / 60;
        int sec = this.duration % 60;

        return String.format("%s - %s (%d:%d min)", performer, title, min, sec);
    }
}
