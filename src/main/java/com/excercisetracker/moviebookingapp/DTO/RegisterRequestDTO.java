package com.excercisetracker.moviebookingapp.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}
