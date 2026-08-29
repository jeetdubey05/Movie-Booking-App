package com.excercisetracker.moviebookingapp.DTO;

import com.excercisetracker.moviebookingapp.Entity.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class BookingDTO {
    private Long bookingId;
    private Integer numberOfSeats;
    private Double totalAmount;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;
    private List<String> seatNumber;
    private Long userId;
    private Long showId;

}
