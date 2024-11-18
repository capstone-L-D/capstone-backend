package com.example.Authentication_servevr.controller;


import com.example.Authentication_servevr.Entity.UserEnt;

import com.example.Authentication_servevr.repo.UserRepo;
import com.example.Authentication_servevr.service.JwtService;
import com.example.Authentication_servevr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerDirector(@RequestBody UserEnt user) {
        user.setRole("ADMIN");
        UserEnt savedUser = userService.register(user);
        return ResponseEntity.ok("Admin registered with ID: " + savedUser.getUserId());
    }

    // Endpoint for registering a new Artist
    @PostMapping("/register/user")
    public ResponseEntity<String> registerArtist(@RequestBody UserEnt user) {
        user.setRole("USER");
        UserEnt savedUser = userService.register(user);
        return ResponseEntity.ok("User registered with ID: " + savedUser.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getToken(@RequestBody UserEnt user) {
        System.out.println("user : " + user);

        UserEnt olduser=userRepo.findByUserMail(user.getUserMail()).get();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserMail(), user.getUserPassword()));
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        if(authenticate.isAuthenticated()) {
            String token= jwtService.generateToken(user.getUserMail());
            Map<String, Object> response = new HashMap<>();
            response.put("id", olduser.getUserId());        // Assuming `getUserId()` is a method in UserCredentialsEntity
            response.put("token", token);                // Add the generated token
            response.put("username", olduser.getUserName());
            response.put("userMail", olduser.getUserMail());
            response.put("role", olduser.getRole());
            System.out.println("USer resposne"+response);
            return ResponseEntity.ok(response);
        }

        return null;
    }
    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token) {
       return userService.verifyToken(token);
    }


    @GetMapping("/sample")
    public String sample() {
        return "Success";
    }
}
