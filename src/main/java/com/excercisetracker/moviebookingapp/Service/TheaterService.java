package com.excercisetracker.moviebookingapp.Service;

import com.excercisetracker.moviebookingapp.DTO.TheaterDTO;
import com.excercisetracker.moviebookingapp.Entity.Theater;
import com.excercisetracker.moviebookingapp.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    public Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return  theaterRepository.save(theater);

    }



    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> listOfTheatre=theaterRepository.findByLocation(location);
        if(listOfTheatre.isPresent()){
            return listOfTheatre.get();
        }
        else throw new RuntimeException("NO THEATER AVAILABLE FOR LOCATION"+location);
    }

    public Theater updateService(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id).orElseThrow(()->new RuntimeException("theater not found with id "));
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return  theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {

        theaterRepository.deleteById(id);

    }
}
