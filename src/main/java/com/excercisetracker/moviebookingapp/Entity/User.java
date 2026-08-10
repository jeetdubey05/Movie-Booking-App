package com.excercisetracker.moviebookingapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Users")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int UserId;
    private String userName;
    private String password;
    private String email;
    private String role;
}
