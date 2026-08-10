package com.excercisetracker.moviebookingapp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="show")
public class Show {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long showId;
    private double price;
    private LocalDateTime showTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="movie_Id",nullable=false)
    private Movie movie;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="id",nullable=false)
    private Theater theater;

  @OneToMany(mappedBy="show",fetch = FetchType.LAZY)
   private List<Booking> bookings;
}
