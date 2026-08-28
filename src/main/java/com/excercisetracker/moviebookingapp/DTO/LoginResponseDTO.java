package com.excercisetracker.moviebookingapp.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class LoginResponseDTO {
    private String jwtToken;
    private String username;
    private Set<String> roles;
}
