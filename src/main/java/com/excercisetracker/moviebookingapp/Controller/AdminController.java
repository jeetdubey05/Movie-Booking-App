package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.RegisterRequestDTO;
import com.excercisetracker.moviebookingapp.Entity.User;
import com.excercisetracker.moviebookingapp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    AuthenticationService authenticationService;
    @Autowired
    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> RegisterNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

        return ResponseEntity.ok(authenticationService.RegisterAdminUser(registerRequestDTO));
    }

}
