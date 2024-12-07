package com.example.mproject.service;


import com.example.mproject.entity.User;
import com.example.mproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
   
    
    // Check if email exists
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Save user to the database
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        
        if (userOptional.isPresent()) {
            return userOptional.get(); // Return the user if found
        }
        return null; // Return null if user not found
    }
    
    // Authenticate admin by email and password
    public boolean authenticateAdmin(String email, String password) {
        User user = userRepository.findByEmail(email);

        // Check if the user exists, passwords match, and the role is "ADMIN"
        return user != null && user.getPassword().equals(password) && "ADMIN".equals(user.getRole());
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User ID does not exist.");
        }
    }
}

