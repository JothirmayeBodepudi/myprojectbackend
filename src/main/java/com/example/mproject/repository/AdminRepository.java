package com.example.mproject.repository;


import com.example.mproject.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByEmailAndPassword(String email, String password);
}

