package com.excercisetracker.moviebookingapp.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Data
public class Theater {
    private Long id;
    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private String theaterScreenType;

    @OneToMany(mappedBy = "theater",fetch=FetchType.LAZY)
    private List<Show> shows;



}
