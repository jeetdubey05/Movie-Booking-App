package com.excercisetracker.moviebookingapp.DTO;

import lombok.Data;

@Data
public class TheaterDTO {
    private Long id;
    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private String theaterScreenType;

}
