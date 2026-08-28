package com.excercisetracker.moviebookingapp.Service;

import com.excercisetracker.moviebookingapp.DTO.LoginRequestDTO;
import com.excercisetracker.moviebookingapp.DTO.LoginResponseDTO;
import com.excercisetracker.moviebookingapp.DTO.RegisterRequestDTO;
import com.excercisetracker.moviebookingapp.Entity.User;
import com.excercisetracker.moviebookingapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }



    public User RegisterNormalUser(RegisterRequestDTO registerRequestDTO) {
       if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
           throw new RuntimeException("user Already Exists");
       }
       Set<String> roles = new HashSet<>();
       roles.add("ROLE_USER");
         User user=new User();
         user.setUsername(registerRequestDTO.getUsername());
         user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
         user.setEmail(registerRequestDTO.getEmail());
         user.setRoles(roles);
         return userRepository.save(user);
    }

    public User RegisterAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("user Already Exists");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
      User user=userRepository.findByUsername(loginRequestDTO.getUsername())
              .orElseThrow(()->new RuntimeException("Username not found"));
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequestDTO.getUsername(),
                      loginRequestDTO.getPassword()
              ));
      String token=jwtService.generateToken(user);
      return LoginResponseDTO.builder()
              .jwtToken(token)
              .username(user.getUsername())
              .roles(user.getRoles())
              .build();
    }
}
