package com.excercisetracker.moviebookingapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name="Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> roles;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private List<Booking> booking=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return roles.stream()
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toList());
    }


}
