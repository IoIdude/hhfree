package com.example.hhfree.repository;

import com.example.hhfree.entity.Employment;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepo extends JpaRepository<Employment, Integer> {
    Employment findByEmploymentName(String name);
}
