package com.excercisetracker.moviebookingapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long bookingId;
    private Integer numberOfSeats;

    private Double totalAmount;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="booking_seat_numbers")
    private List<String> seatNumber;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="show_id",nullable = false)
    private Show show;

}
