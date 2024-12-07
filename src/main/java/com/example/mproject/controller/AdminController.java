package com.example.mproject.controller;

import com.example.mproject.entity.User;
import com.example.mproject.model.Role;
import com.example.mproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AdminController {

    @Autowired
    private UserService userService;

    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        boolean isAuthenticated = userService.authenticateAdmin(email, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful"); // Status 200
        } else {
            return ResponseEntity.status(401).body("Invalid credentials or not an admin"); // Status 401
        }
}
    
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
