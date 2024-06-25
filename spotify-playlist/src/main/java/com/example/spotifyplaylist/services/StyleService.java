package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.entities.Style;
import com.example.spotifyplaylist.domain.enums.StyleName;

public interface StyleService {
    void initializeStyles();
    Style findAllByStyleName(StyleName styleName);
}
