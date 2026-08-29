package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.movieDTO;
import com.excercisetracker.moviebookingapp.Entity.Movie;
import com.excercisetracker.moviebookingapp.Service.movieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class movieController {
    @Autowired
    private movieService movieService;
    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(movieDTO movieDTO ) {
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }
    @GetMapping("/allmovies")
    public ResponseEntity<List<Movie>>getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovie());
    }
    @GetMapping("/getbygenre")
    public ResponseEntity<List<Movie>> getAllMoviesByGenre(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMovieByGenre(String genre));
    }
    @GetMapping("/getbylanguage")
    public ResponseEntity<List<Movie>> getAllMoviesByLanguage(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMovieByLanguage(String genre));
    }
    @GetMapping("/getbytitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }
    @PutMapping("/updatemovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id,@RequestBody movieDTO movieDTO){
        return ResponseEntity.ok(movieService.updateMovies(id,movieDTO);
    }
    @DeleteMapping("/deletemovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

}
