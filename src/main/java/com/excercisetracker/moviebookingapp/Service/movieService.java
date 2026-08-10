package com.excercisetracker.moviebookingapp.Service;


import com.excercisetracker.moviebookingapp.DTO.movieDTO;
import com.excercisetracker.moviebookingapp.Entity.Movie;
import com.excercisetracker.moviebookingapp.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class movieService {
    @Autowired
    private MovieRepository movieRepository;
    public Movie addMovie(movieDTO movieDTO) {
         Movie movie=new Movie();
         movie.setTitle(movieDTO.getTitle());
         movie.setGenre(movieDTO.getGenre());
         movie.setReleaseDate(movieDTO.getReleaseDate());
         movie.setDescription(movieDTO.getDescription());
         movie.setLanguage(movieDTO.getLanguage());
         movie.setReleaseDate(movieDTO.getReleaseDate());
         return movieRepository.save(movie);
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }
    public List<Movie> getMovieByGenre(String genre) {
        Optional<List<Movie>> listOfMovie= movieRepository.findByGenre(genre);
       if(listOfMovie.isPresent()){
           return listOfMovie.get();
       }
       else throw new RuntimeException("Movie not found with genre"+genre);
    }
    public List<Movie> getMovieByLanguage(String genre) {
        Optional<List<Movie>> listOfMovie= movieRepository.findByLanguage(genre);
        if(listOfMovie.isPresent()){
            return listOfMovie.get();
        }
        else throw new RuntimeException("Movie not found with Language "+genre);
    }
    public Movie getMovieByTitle(String title){
        Optional<Movie> movieBox= movieRepository.findByTitle(title);
        if(movieBox.isPresent()){
            return movieBox.get();
        }
        else throw new RuntimeException("Movie not found with title"+title);
    }
    public Movie updateMovie(Long id,movieDTO movieDTO) {
        Movie movie=movieRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Movie not found with id"+id));
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDescription(movieDTO.getDescription());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        return movieRepository.save(movie);
    }
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);

    }
}
