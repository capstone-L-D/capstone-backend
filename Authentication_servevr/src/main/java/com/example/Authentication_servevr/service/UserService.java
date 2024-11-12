package com.example.Authentication_servevr.service;


import com.example.Authentication_servevr.Entity.UserEnt;
import com.example.Authentication_servevr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEnt register(UserEnt user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword())); // Encrypt password
        return userRepo.save(user);
    }

    public boolean verifyToken(String token) {
        jwtService.validateToken(token);
        return true;
    }
}
