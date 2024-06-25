package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.entities.Style;
import com.example.spotifyplaylist.domain.enums.StyleName;
import com.example.spotifyplaylist.repositories.StyleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    @PostConstruct
    public void initializeStyles() {
        if (this.styleRepository.count() == 0) {

            List<Style> allStyles = Arrays.stream(StyleName.values())
                    .map(styleName -> new Style(styleName))
                    .collect(Collectors.toList());

//            List<Style> allStyles = Arrays.stream(StyleName.values())
//                    .map(Style::new)
//                    .toList();

            this.styleRepository.saveAllAndFlush(allStyles);
        }
    }

    @Override
    public Style findAllByStyleName(StyleName styleName) {
        return this.styleRepository.findByStyleName(styleName).get();
    }
}
