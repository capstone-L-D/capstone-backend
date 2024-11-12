package com.example.LMS_UserAuthentication.service;

import com.example.LMS_UserAuthentication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserDetails implements UserDetails {

        private String name;
        private String password;


        public CustomUserDetails(User user) {
            this.name = user.getUserName();
            this.password = user.getPassword();
        }



        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return name;
        }
    }


