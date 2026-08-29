package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.ShowDTO;
import com.excercisetracker.moviebookingapp.Entity.Show;
import com.excercisetracker.moviebookingapp.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class showController {

    private  ShowService showService;

    @Autowired
    public showController(ShowService showService) {
        this.showService = showService;
    }
    @PostMapping("/createmovie")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDto){
     return ResponseEntity.ok().body(showService.createShow(showDto));
    }
    @GetMapping("/allmovies")
    public ResponseEntity<List<Show>> getAllShow(){
        return ResponseEntity.ok(showService.getAllShow());
    }
    @GetMapping("/showbymovie/{id}")
    public ResponseEntity<List<Show>> getShowByMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(showService.getShowByMovie(movieId));
    }
    @GetMapping("/showbytheater/{id}")
    public ResponseEntity<List<Show>> getShowByTheater(@PathVariable Long theaterId){
        return ResponseEntity.ok(showService.getShowByTheater(theaterId));
    }
    @PutMapping("/updateshow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long showId,@RequestBody ShowDTO showDTO){
        showService.updateShow(showId,showDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deletemovies/{id}")
    public ResponseEntity<Void> DeleteShow(@PathVariable Long showId){
        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }

}
