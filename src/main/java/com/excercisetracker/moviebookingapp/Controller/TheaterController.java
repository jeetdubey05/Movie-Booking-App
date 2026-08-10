package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.TheaterDTO;
import com.excercisetracker.moviebookingapp.Entity.Theater;
import com.excercisetracker.moviebookingapp.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/addtheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO) {
        return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
    }
    @GetMapping("/getbylocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location) {

        return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }
    @PutMapping("/updatetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody TheaterDTO theaterDTO) {
      return ResponseEntity.ok(theaterService.updateService(id,theaterDTO));
    }

    @DeleteMapping("/deletetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }



}
