package com.excercisetracker.moviebookingapp.Repository;

import com.excercisetracker.moviebookingapp.Entity.Booking;
import com.excercisetracker.moviebookingapp.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long> {
 List<Booking> findByUserId(Long UserId);
 List<Booking> findByBookingStatus(BookingStatus bookingStatus);
List<Booking> findByShowId(Long showId);
}
