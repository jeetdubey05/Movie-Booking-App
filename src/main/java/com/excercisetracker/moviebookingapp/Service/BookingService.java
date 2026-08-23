package com.excercisetracker.moviebookingapp.Service;



import com.excercisetracker.moviebookingapp.DTO.BookingDTO;
import com.excercisetracker.moviebookingapp.Entity.Booking;
import com.excercisetracker.moviebookingapp.Entity.BookingStatus;
import com.excercisetracker.moviebookingapp.Entity.Show;
import com.excercisetracker.moviebookingapp.Entity.User;
import com.excercisetracker.moviebookingapp.Repository.BookingRepository;
import com.excercisetracker.moviebookingapp.Repository.ShowRepository;
import com.excercisetracker.moviebookingapp.Repository.TheaterRepository;
import com.excercisetracker.moviebookingapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;
    private final UserRepository userRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, TheaterRepository theaterRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.theaterRepository = theaterRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(BookingDTO bookingDTO) {
        Show show=showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()-> new RuntimeException("Show Not Found"));
        if(!isSeatAvailable( show.getShowId(), bookingDTO.getNumberOfSeats())){
            throw new RuntimeException("Seat Not Available");
        }
        if(bookingDTO.getSeatNumber().size()!=bookingDTO.getNumberOfSeats()){
            throw new RuntimeException("Seat Number and no of seats should match ");
        }
        validateDuplicateSeats(show.getShowId(),bookingDTO.getSeatNumber());
        User user=userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(()->new RuntimeException("user not found with id"+bookingDTO.getUserId()));
        Booking booking =new Booking();
        booking.setShow(show);
        booking.setUser(user);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingTime(LocalDateTime.now());
        booking.setSeatNumber(bookingDTO.getSeatNumber());
        booking.setTotalAmount(CalculateAmount(show.getPrice(),bookingDTO.getNumberOfSeats()));
        return bookingRepository.save(booking);

    }
    public Boolean isSeatAvailable(Long showId,Integer numberOfSeats){
        Show show=showRepository.findById(showId)
                .orElseThrow(()-> new RuntimeException("Show Not Found"));
        int BookedSeats=show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus()!=BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (show.getTheater().getTheaterCapacity()-BookedSeats)>=numberOfSeats;
    }
    private  Double CalculateAmount(Double price,Integer noOfSeats){
        return price*noOfSeats;
    }


public void validateDuplicateSeats(Long showId,List<String> seatNumbers){
    Show show=showRepository.findById(showId)
            .orElseThrow(()-> new RuntimeException("Show Not Found"));
    Set<String> occupiedSeats=show.getBookings().stream()
            .filter(b->b.getBookingStatus()!=BookingStatus.CANCELLED)
            .flatMap(b->b.getSeatNumber().stream())
            .collect(Collectors.toSet());
    List<String> duplicateSeats=seatNumbers.stream()
            .filter(occupiedSeats::contains)
            .collect(Collectors.toList());

    if(duplicateSeats.isEmpty()){
        throw new  RuntimeException("these seats are already Booked");
    }}
    public List<Booking> getAllBooking(Long userId) {
        /*User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not found with ID"));*/
     return  bookingRepository.findByUserId(userId);

    }

    public List<Booking> getShowBooking(Long showId) {
        return  bookingRepository.findByShowId(showId);
    }

    public Booking confirmBooking(Long bookingId) {
        Booking booking=bookingRepository.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking not Found"));
        if(booking.getBookingStatus()!=BookingStatus.PENDING){
            throw new RuntimeException("Booking Status Not Pending");
        }
        // After payment is confirmed by the payment gateway
        booking.setBookingStatus(BookingStatus.BOOKED);
         return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking=bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Booking Not Found"));
        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    private void validateCancellation(Booking booking) {
        LocalDateTime showTime=booking.getShow().getShowTime();
        LocalDateTime deadlineTime=showTime.minusHours(2);
        if(LocalDateTime.now().isAfter(deadlineTime)){
            throw new RuntimeException("cannot cancel Booking");
        }
        if(booking.getBookingStatus()!=BookingStatus.CANCELLED){
            throw new RuntimeException("Booking is Already Cancelled");
        }
    }

    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
        return bookingRepository.findByBookingStatus(bookingStatus);

    }
}
