package com.example.spotifyplaylist.repositories;

import com.example.spotifyplaylist.domain.entities.Song;
import com.example.spotifyplaylist.domain.entities.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<List<Song>> findAllByStyle(Style style);
}

