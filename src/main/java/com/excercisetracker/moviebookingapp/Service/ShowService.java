package com.excercisetracker.moviebookingapp.Service;

import com.excercisetracker.moviebookingapp.DTO.ShowDTO;
import com.excercisetracker.moviebookingapp.Entity.Booking;
import com.excercisetracker.moviebookingapp.Entity.Movie;
import com.excercisetracker.moviebookingapp.Entity.Show;
import com.excercisetracker.moviebookingapp.Entity.Theater;
import com.excercisetracker.moviebookingapp.Repository.ShowRepository;
import com.excercisetracker.moviebookingapp.Repository.MovieRepository;
import com.excercisetracker.moviebookingapp.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private ShowRepository showRepository;
    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;
    @Autowired
    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    public Show createShow(ShowDTO showDto) {
     Movie movie=movieRepository.findById(showDto.getMovieId())
             .orElseThrow(()->new RuntimeException("Movie not found with ID "+showDto.getMovieId()));
        Theater theater=theaterRepository.findById(showDto.getTheaterId())
                .orElseThrow(()->new RuntimeException("Theater not found with ID "+showDto.getTheaterId()));
        Show show=new Show();
        show.setShowTime(showDto.getShowTime());
        show.setPrice(showDto.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
       return  showRepository.save(show);
    }

    public List<Show> getAllShow() {
       return showRepository.findAll();
    }

    public List<Show> getShowByMovie(Long movieId) {
        Optional<List<Show>> showBox = showRepository.findByMovieId(movieId);
        if (showBox.isPresent()) {
            return showBox.get();
        } else throw new RuntimeException("no show available for the movie " + movieId);
    }
    public List<Show> getShowByTheater(Long theaterId) {
    Optional<List<Show>> showList=showRepository.findByTheaterId(theaterId);
    if (showList.isPresent()) {
        return showList.get();
    }
    else throw new RuntimeException("no show available " + theaterId);
    }

    public void updateShow(Long showId, ShowDTO showDTO) {
        Show show=showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found with ID "+showId));
        Movie movie=movieRepository.findById(showDTO.getMovieId())
                        .orElseThrow(()->new RuntimeException("Movie not found with ID "+showDTO.getMovieId()));
        Theater theater=theaterRepository.findById(showDTO.getTheaterId())
                        .orElseThrow(()->new RuntimeException("theater not found with this id"));
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

    }

    public void deleteShow(Long showId) {
       if(!showRepository.existsById(showId)) {
           throw new RuntimeException("show not found with ID "+showId);
       }
       List<Booking> bookings=showRepository.findById(showId).get().getBookings();
       if(bookings.isEmpty()){
           throw new RuntimeException("booking  found with ID "+showId);
       }
       showRepository.deleteById(showId);
    }
}




