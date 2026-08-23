package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.BookingDTO;
import com.excercisetracker.moviebookingapp.Entity.Booking;
import com.excercisetracker.moviebookingapp.Entity.BookingStatus;
import com.excercisetracker.moviebookingapp.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private final BookingService bookingService;

    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }
    @GetMapping("/getuserbooking/{id}")
    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getAllBooking(userId));
    }
    @GetMapping("/getshowbooking/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long showId){
        return ResponseEntity.ok(bookingService.getShowBooking(showId));
    }
    @PutMapping("{id}/confirm")
    public ResponseEntity<List<Booking>> confirmBooking(Long bookingId) {
        return  ResponseEntity.ok(bookingService.confirmBooking(bookingId));
    }
    @PutMapping("{id}/cancel")
    public ResponseEntity<List<Booking>> cancelBooking(Long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
    @GetMapping("/bookingstatus/{booking status}")
    public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable BookingStatus bookingStatus){
        return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
    }

}
