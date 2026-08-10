package com.excercisetracker.moviebookingapp.Repository;

import com.excercisetracker.moviebookingapp.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long> {
}
