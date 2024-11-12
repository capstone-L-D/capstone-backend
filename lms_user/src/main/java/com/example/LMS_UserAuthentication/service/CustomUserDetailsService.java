package com.example.LMS_UserAuthentication.service;

import com.example.LMS_UserAuthentication.entity.User;
import com.example.LMS_UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {


        @Autowired
        UserRepository repo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user = repo.findByUserName(username);
            System.out.println("user 2: " + user);
            return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username/password not valid!"));
        }
    }

