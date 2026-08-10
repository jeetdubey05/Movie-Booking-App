package com.excercisetracker.moviebookingapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="movies")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private String description;
    private String genre;
    private String language;
    private Integer duration;
    private LocalDate releaseDate;

    @OneToMany(mappedBy="movie",fetch=FetchType.LAZY)
    private List<Show> shows;
}
