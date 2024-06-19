package com.example.spotifyplaylist.repositories;

import com.example.spotifyplaylist.domain.entities.Style;
import com.example.spotifyplaylist.domain.enums.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    Optional<Style> findByStyleName(StyleName styleName);
}
