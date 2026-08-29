package com.excercisetracker.moviebookingapp.Controller;

import com.excercisetracker.moviebookingapp.DTO.LoginRequestDTO;
import com.excercisetracker.moviebookingapp.DTO.LoginResponseDTO;
import com.excercisetracker.moviebookingapp.DTO.RegisterRequestDTO;
import com.excercisetracker.moviebookingapp.Entity.User;
import com.excercisetracker.moviebookingapp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private final AuthenticationService authenticationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> RegisterNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.RegisterNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authenticationService.loginUser(loginRequestDTO));
    }


}
