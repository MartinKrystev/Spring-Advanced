package com.example.spotifyplaylist.domain.entities;

import com.example.spotifyplaylist.domain.enums.StyleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style-name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleName styleName;

    @Column
    private String description;

    public Style(StyleName styleName) {
        this.styleName = styleName;
        this.description = "The song's style is " + styleName.toString();
    }

}
