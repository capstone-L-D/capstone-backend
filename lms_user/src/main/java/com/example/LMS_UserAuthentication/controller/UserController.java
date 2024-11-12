package com.example.LMS_UserAuthentication.controller;

import com.example.LMS_UserAuthentication.entity.User;
import com.example.LMS_UserAuthentication.service.JwtService;
import com.example.LMS_UserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {



        @Autowired
        JwtService jwtService;

        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @PostMapping("/register")
        public User register(@RequestBody User user) {
            return userService.register(user);
        }

        @GetMapping("/validate/token")
        public boolean validateToken(@RequestParam String token) {
            return userService.verifyToken(token);
        }

        @PostMapping("/validate/user")
        public String getToken(@RequestBody User user) {
            System.out.println("user : " + user);
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
            System.out.println("authenticated?? : " + authenticate.isAuthenticated());
            if(authenticate.isAuthenticated()) {
                return userService.generateToken(user.getUserName());
            }
            return null;
        }
    }


