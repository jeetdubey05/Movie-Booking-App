package com.excercisetracker.moviebookingapp.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequestDTO {
    private String username;
    private String password;

}
