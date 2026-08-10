package com.excercisetracker.moviebookingapp.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class movieDTO {
    private Long MovieId;
    private String title;
    private String description;
    private String genre;
    private String language;
    private Integer duration;
    private LocalDate releaseDate;

}
