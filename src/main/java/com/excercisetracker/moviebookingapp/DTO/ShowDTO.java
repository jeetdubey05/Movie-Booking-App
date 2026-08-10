package com.excercisetracker.moviebookingapp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class ShowDTO {
    private LocalDateTime showTime;
    private Double price;
    private Long movieId;
    private Long theaterId;

}
