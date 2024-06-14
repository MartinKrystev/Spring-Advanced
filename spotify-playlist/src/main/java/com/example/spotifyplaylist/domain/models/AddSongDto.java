package com.example.spotifyplaylist.domain.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddSongDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String performer;

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Positive
    private int duration;

    @NotNull
    private String style;

}
