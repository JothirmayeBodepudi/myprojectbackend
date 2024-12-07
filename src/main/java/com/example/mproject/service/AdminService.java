package com.example.mproject.service;

import com.example.mproject.entity.Admin;
import com.example.mproject.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean validateAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password) != null;
    }
    
}
